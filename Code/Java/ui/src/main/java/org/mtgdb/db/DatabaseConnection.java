package org.mtgdb.db;

import org.apache.commons.io.IOUtils;
import org.mtgdb.util.Constants;
import org.mtgdb.util.assertion.Assert;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sandro Orlando
 */
public final class DatabaseConnection implements IDatabaseConnection {

  private Path path;
  private Connection connection;
  private Transaction currentTransaction;

  public DatabaseConnection() {
  }

  private void open(final Path path) {
    this.path = path;
    try {
      Class.forName("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:" + this.path.toAbsolutePath().toFile());
      connection.setAutoCommit(false);
      if (!isSchemaComplete()) {
        createDB();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private boolean isSchemaComplete() {
    try {
      final Statement statement = connection.createStatement();
      final ResultSet execute = statement.executeQuery("SELECT  * FROM INFORMATION_SCHEMA.TABLES WHERE  (TABLE_NAME= 'Edition' OR TABLE_NAME = 'MagicCard' OR TABLE_NAME = 'PhysicalCard' OR TABLE_NAME = 'Container' OR TABLE_NAME = 'Deck' OR TABLE_NAME = 'CardDescriptionXDeck') ");
      execute.last();
      boolean result = execute.getRow() == 6;
      execute.close();
      statement.close();
      return result;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  private void createDB() {
    try {
      if (connection == null || connection.isClosed()) throw new RuntimeException("Database is not open");
      final Statement statement = connection.createStatement();
      String content;
      try {
        content = IOUtils.toString(getClass().getResource("/CreateTables.sql"), Constants.UTF_8);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      statement.execute(content);
      statement.close();
    } catch (SQLException e) {
      Assert.log(e, "No connection to the database");
    }
  }

  @Override
  public ResultSet executeQuery(String queryString) throws SQLException {
    Statement statement = connection.createStatement();
    return statement.executeQuery(queryString);
  }

  @Override
  public void openDB() {
    open(FileSystems.getDefault().getPath("data.db"));
  }

  @Override
  public void executeSql(final String s) {
    try {
      final Statement statement = connection.createStatement();
      statement.execute(s);
      statement.close();
    } catch (SQLException e) {
      Assert.log(e);
    }
  }

  @Override
  public void execute(final ITransactionRunnable runnable) {
    if (currentTransaction != null) throw new IllegalStateException("nested transaction are not possible at the moment, please comeback later");
    try {
      if (currentTransaction == null) {
        currentTransaction = new Transaction();
      }
      runnable.run(currentTransaction);
      currentTransaction.commit();
      currentTransaction = null;
    } catch (Exception e) {
      if (currentTransaction != null) {
        try {
          currentTransaction.abort();
        } catch (SQLException e1) {
          throw new RuntimeException("error during rollback", e1);
        }
      }
      throw new RuntimeException("error during transaction", e);
    }
  }

  @Override
  public void closeDB() {
    try {
      connection.commit();
      connection.close();
    } catch (SQLException e) {
      Assert.log(e);
    }
  }

  private class Transaction implements ITransaction {

    @Override
    public ResultSet insert(final String s) {
      try {
        final PreparedStatement preparedStatement = connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    public void commit() throws SQLException {
      connection.commit();
    }

    public void abort() throws SQLException {
      connection.rollback();
    }
  }
}
