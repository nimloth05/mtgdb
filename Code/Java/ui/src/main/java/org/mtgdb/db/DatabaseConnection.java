package org.mtgdb.db;

import org.apache.commons.io.IOUtils;
import org.mtgdb.util.Constants;
import org.mtgdb.util.assertion.Assert;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sandro Orlando
 */
public final class DatabaseConnection implements IDatabaseConnection {

  private final Path path;
  private Connection connection;

  public DatabaseConnection(final Path path) {
    this.path = path;
  }

  public static IDatabaseConnection create() {
    DatabaseConnection connection = new DatabaseConnection(FileSystems.getDefault().getPath("data.db"));
    connection.open();
    return connection;
  }

  private void open() {
    try {
      Class.forName("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:" + path.toAbsolutePath().toFile());
      if (path.toFile().exists()) {
        openExistingDB();
      } else {
        createDB();
        openExistingDB();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
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

  private void openExistingDB() {

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
}
