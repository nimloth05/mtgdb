package org.mtgdb.util;

/**
 * @author Sandro Orlando
 */
public final class EscapeUtils {

  private EscapeUtils() {}

  public static String escapeSQL(String value) {
    value = value.replace("\"", "");
    value = value.replace("'", "''");
    return value;
  }

}
