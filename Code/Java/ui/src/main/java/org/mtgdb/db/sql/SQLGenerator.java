package org.mtgdb.db.sql;

/**
 * @author Sandro Orlando
 */
public final class SQLGenerator {

  private SQLGenerator() {
  }

  public static String insertInto(String table, Column... columns) {
    StringBuilder builder = new StringBuilder();
    builder
      .append("insert into \"")
      .append(table)
      .append("\" (");
    for (Column colum : columns) {
      builder
        .append("\"")
        .append(colum.getName())
        .append("\",");
    }
    builder.deleteCharAt(builder.length() -1);
    builder.append(")");

    builder.append(" values (");
    for (Column column : columns) {
      builder
        .append(column.getValue())
        .append(",");
    }
    builder.deleteCharAt(builder.length()-1);
    builder.append(")");
    return builder.toString();
  }

}
