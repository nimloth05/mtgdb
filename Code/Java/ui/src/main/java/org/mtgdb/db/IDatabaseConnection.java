package org.mtgdb.db;

/**
 * @author Sandro Orlando
 */
public interface IDatabaseConnection {

  void executeSql(String s);

  void closeDB();
}
