package org.mtgdb.db.sql;

/**
 * @author Sandro Orlando
 */
public final class SQLGenerator {

  private SQLGenerator() {
  }

  public static String insertInto(String table, Column[] columns, Value[][] rows) {
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
    builder.deleteCharAt(builder.length() - 1);
    builder.append(")");

    builder.append(" values (");
    for (Value[] row : rows) {
      for (Value value : row) {
        builder
          .append(value.getValue())
          .append(",");
      }
      builder.deleteCharAt(builder.length() - 1);
      builder.append(")");
      builder.append(",(");
    }
    builder.deleteCharAt(builder.length() - 2);
    builder.deleteCharAt(builder.length() - 1);
    return builder.toString();
  }

}
