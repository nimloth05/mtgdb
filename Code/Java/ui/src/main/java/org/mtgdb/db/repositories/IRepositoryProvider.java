package org.mtgdb.db.repositories;

/**
 * @author Sandro Orlando
 */
public interface IRepositoryProvider {

  <T extends IRepository> T getRepository(Class<?> entityType);
}
