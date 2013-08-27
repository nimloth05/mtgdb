package org.mtgdb.db;

import org.mtgdb.util.dispose.IDisposable;

/**
 * @author Sandro Orlando
 */
public interface IDatabaseConnection {

  void openDB();

  void closeDB();

  void execute(ITransactionRunnable runnable);

  IDisposable addListener(IDatabaseConnectionListener listener);
}
