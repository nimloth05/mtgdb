package org.mtgdb.db;

/**
 * @author Sandro Orlando
 */
public interface ITransactionRunnable {


  public void run(final ITransaction transaction) throws Exception;

}
