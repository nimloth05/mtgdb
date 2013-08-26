package org.mtgdb.db;

import org.mtgdb.model.Container;

/**
 * @author Sandro Orlando
 */
public interface IContainerRepository extends IRepository {

  void save(ITransaction transaction, Container container);
}
