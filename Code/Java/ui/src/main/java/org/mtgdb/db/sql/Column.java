package org.mtgdb.db.sql;

/**
 * @author Sandro Orlando
 */
public final class Column {

  private String name;
  public Column() {

  }

  public Column(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
