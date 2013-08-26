package org.mtgdb.db.repositories;

import org.mtgdb.db.ITransaction;
import org.mtgdb.model.Container;

/**
 * @author Sandro Orlando
 */
public interface IContainerRepository extends IRepository {

  void save(ITransaction transaction, Container container);
}
