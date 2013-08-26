package org.mtgdb.services;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.mtgdb.db.ContainerRepository;
import org.mtgdb.db.DatabaseConnection;
import org.mtgdb.db.EditionRepository;
import org.mtgdb.db.IContainerRepository;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.IEditionRepository;
import org.mtgdb.db.IMagicCardRepository;
import org.mtgdb.db.MagicCardRepository;

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
        bind(IMagicCardRepository.class).to(MagicCardRepository.class);
        bind(IEditionRepository.class).to(EditionRepository.class);
        bind(IContainerRepository.class).to(ContainerRepository.class);
      }
    });
  }

  public <T> T doGet(final Class<T> type) {
    return injector.getInstance(type);
  }

  public static <T> T get(final Class<T> type) {
    return instance.doGet(type);
  }



}
