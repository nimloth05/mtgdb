package org.mtgdb.db.repository;

/**
 * @author Sandro Orlando
 */
public interface IRepositoryProvider {

  <T extends IRepository> T getRepository(Class<?> entityType);
}
