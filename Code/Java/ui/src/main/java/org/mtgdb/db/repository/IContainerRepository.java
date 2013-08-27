package org.mtgdb.db.repository;

import org.mtgdb.db.ITransactionToken;
import org.mtgdb.model.Container;

/**
 * @author Sandro Orlando
 */
public interface IContainerRepository extends IRepository<Container> {

  void save(ITransactionToken transaction, Container container);
}
