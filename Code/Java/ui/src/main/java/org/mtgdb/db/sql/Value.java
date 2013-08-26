package org.mtgdb.db.sql;

import org.apache.commons.lang.StringUtils;
import org.mtgdb.util.Constants;
import org.mtgdb.util.EscapeUtils;

/**
 * @author Sandro Orlando
 */
public final class Value {

  private String value;

  public Value(final int value) {
    this.value = Integer.toString(value);
  }

  public Value(final String value) {
    this.value = "'" + StringUtils.defaultIfEmpty(escape(value), Constants.EMPTY) + "'";
  }

  public static String escape(final String value) {
    if (value == null) return null;
    return EscapeUtils.escapeSQL(value);
  }

  public String getValue() {
    return value;
  }
}
