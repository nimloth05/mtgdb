package org.mtgdb.db.repository;

import org.mtgdb.model.Edition;

/**
 * @author Sandro Orlando
 */
public interface IEditionRepository extends IRepository<Edition> {

  void save(Edition edition);

  void deleteAll();
}
