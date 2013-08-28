package org.mtgdb.db;

import org.mtgdb.util.dispose.IDisposable;

/**
 * @author Sandro Orlando
 */
public interface IDatabaseConnection {

  void openDB();

  void closeDB();

  IDisposable addListener(IDatabaseConnectionListener listener);

  void execute(ITransactionRunnable runnable);
}
