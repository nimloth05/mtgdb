package org.mtgdb.db.repository;

import java.util.List;

/**
 * @author Sandro Orlando
 */
public interface IRepository<T> {

  public List<T> getAll();

}
