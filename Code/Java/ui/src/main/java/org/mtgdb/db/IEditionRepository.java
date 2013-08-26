package org.mtgdb.db;

import org.mtgdb.model.Edition;

/**
 * @author Sandro Orlando
 */
public interface IEditionRepository extends IRepository {

  void save(ITransaction transaction, Edition edition);

  void deleteAll(ITransaction transaction);
}
