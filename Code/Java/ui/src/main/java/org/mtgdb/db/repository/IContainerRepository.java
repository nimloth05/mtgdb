package org.mtgdb.db.repository;

import org.mtgdb.model.Container;

import java.util.List;

/**
 * @author Sandro Orlando
 */
public interface IContainerRepository extends IRepository<Container> {

  void save(Container container);

  void removeAll(List<Container> removeContainers);
}
