package org.mtgdb.db;

import com.j256.ormlite.support.ConnectionSource;

/**
 * @author Sandro Orlando
 */
public interface IDatabaseConnectionListener {

  public void opened(final ConnectionSource connection);

  public void closing(final ConnectionSource connection);

}
