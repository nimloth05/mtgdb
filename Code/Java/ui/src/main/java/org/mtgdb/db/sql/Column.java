package org.mtgdb.db.sql;

import org.mtgdb.util.EscapeUtils;

/**
 * @author Sandro Orlando
 */
public final class Column {

  private String name;
  private String value;

  public Column() {

  }

  public Column(final String name, final String value) {
    this.name = name;
    this.value = "'" + escape(value) + "'";
  }

  public Column(final String name, final int value) {
    this.name = name;
    this.value = Integer.toString(value);
  }

  public static String escape(final String value) {
    return EscapeUtils.escapeSQL(value);
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(final String value) {
    this.value = value;
  }
}
