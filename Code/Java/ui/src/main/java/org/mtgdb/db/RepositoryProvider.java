package org.mtgdb.db;

import com.google.inject.Inject;
import org.mtgdb.model.CardDescription;
import org.mtgdb.model.Container;
import org.mtgdb.model.Edition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sandro Orlando
 */
public final class RepositoryProvider implements IRepositoryProvider {

  private final Map<Class<?>, IRepository> repositories;

  @Inject
  public RepositoryProvider(final IDatabaseConnection connection) {
    repositories = createRepositories(connection);
  }

  private Map<Class<?>, IRepository> createRepositories(final IDatabaseConnection connection) {
    Map<Class<?>, IRepository> repos = new HashMap<Class<?>, IRepository>();
    repos.put(CardDescription.class, new MagicCardRepository(connection));
    repos.put(Container.class, new ContainerRepository());
    repos.put(Edition.class, new EditionRepository());
    return repos;
  }

  @Override
  public <T extends IRepository> T getRepository(final Class<?> entityType) {
    final IRepository repository = this.repositories.get(entityType);
    if (repository == null) throw new IllegalArgumentException("coult not find repository for " + entityType.getName());
    return (T) repository;
  }

}
