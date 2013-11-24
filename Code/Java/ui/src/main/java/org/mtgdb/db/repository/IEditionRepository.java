package org.mtgdb.db.repository;

import org.mtgdb.model.Edition;

import java.util.Map;

/**
 * @author Sandro Orlando
 */
public interface IEditionRepository extends IRepository<Edition> {

  void save(Edition edition);

  void deleteAll();

  void delete(Edition edition);

  void gatherEditionInformation(Map<String, Integer> editionToNumberOfCards);
}
