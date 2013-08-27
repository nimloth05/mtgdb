package org.mtgdb.db.repository;

import org.mtgdb.db.ITransactionToken;
import org.mtgdb.model.Edition;

/**
 * @author Sandro Orlando
 */
public interface IEditionRepository extends IRepository<Edition> {

  void save(ITransactionToken transaction, Edition edition);

  void deleteAll(ITransactionToken transaction);
}
