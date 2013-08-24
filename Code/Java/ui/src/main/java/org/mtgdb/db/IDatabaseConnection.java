package org.mtgdb.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sandro Orlando
 */
public interface IDatabaseConnection {

  void executeSql(String s);

  void closeDB();

  ResultSet executeQuery(String s) throws SQLException;
}
