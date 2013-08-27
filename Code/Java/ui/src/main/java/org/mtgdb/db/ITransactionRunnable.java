package org.mtgdb.db;

/**
 * @author Sandro Orlando
 */
public interface ITransactionRunnable {


  public void run(final ITransactionToken transaction) throws Exception;

}
