package org.mtgdb.db;

/**
 * @author Sandro Orlando
 */
public interface IRepositoryProvider {

  <T extends IRepository> T getRepository(Class<?> entityType);
}
