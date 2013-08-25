package org.mtgdb.db;

import java.sql.ResultSet;

/**
 * @author Sandro Orlando
 */
public interface ITransaction {

  /**
   *
   * @param s sql
   * @return generated primary keys
   */
  ResultSet insert(String s);
}
