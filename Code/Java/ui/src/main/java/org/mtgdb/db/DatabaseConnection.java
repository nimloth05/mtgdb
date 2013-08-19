package org.mtgdb.db;

import org.mtgdb.util.assertion.Assert;

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

  public DatabaseConnection(final Path path) {
    this.path = path;
  }

  public static IDatabaseConnection create() {
    DatabaseConnection connection = new DatabaseConnection(FileSystems.getDefault().getPath("data.db"));
    connection.open();
    return connection;
  }

  private void open() {
    if (path.toFile().exists()) {
      openExistingDB();
    } else {
      createDB();
      openExistingDB();
    }
  }

  private void createDB() {
    try {
      Class.forName("org.h2.Driver");
      final Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
      final Statement statement = connection.createStatement();
      statement.execute("create table " + DBConstants.EDITION_TABLE);
      statement.close();
    } catch (SQLException e) {
      Assert.log(e, "No connection to the database");
    } catch (ClassNotFoundException e) {
      Assert.log(e, "Error loading database driver");
    }
  }

  private void openExistingDB() {

  }

}
