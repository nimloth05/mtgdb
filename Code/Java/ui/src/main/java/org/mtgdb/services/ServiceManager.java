package org.mtgdb.services;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.mtgdb.db.DatabaseConnection;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.IRepositoryProvider;
import org.mtgdb.db.RepositoryProvider;

/**
 * @author Sandro Orlando
 */
public final class ServiceManager {

  public static final ServiceManager instance = new ServiceManager();
  private Injector injector;

  private ServiceManager() {
    createGuice();
  }

  private void createGuice() {
    this.injector = Guice.createInjector(new AbstractModule() {
      @Override
      protected void configure() {
        bind(IDatabaseConnection.class).toInstance(new DatabaseConnection());
        bind(IRepositoryProvider.class).to(RepositoryProvider.class);
      }
    });
  }

  public <T> T get(final Class<T> type) {
    return injector.getInstance(type);
  }


}
