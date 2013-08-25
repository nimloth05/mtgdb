package org.mtgdb.db;

import org.mtgdb.util.EscapeUtils;

/**
 * @author Sandro Orlando
 */
public abstract class AbstractRepository implements IRepository{

  protected String escape(String value) {
    return EscapeUtils.escapeSQL(value);
  }


}
