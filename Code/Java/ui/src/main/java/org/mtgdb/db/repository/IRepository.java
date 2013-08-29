package org.mtgdb.db.repository;

import java.util.Collection;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public interface IRepository<T> {

  public List<T> getAll();

  void saveAll(Collection<T> objects);

  void save(T container);
}
