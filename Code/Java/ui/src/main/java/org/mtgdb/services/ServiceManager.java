package org.mtgdb.services;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.mtgdb.db.DatabaseConnection;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.repository.CardMemoryRepository;
import org.mtgdb.db.repository.ContainerRepository;
import org.mtgdb.db.repository.DeckMagicCardRepository;
import org.mtgdb.db.repository.EditionRepository;
import org.mtgdb.db.repository.ICardMemoryRepository;
import org.mtgdb.db.repository.IContainerRepository;
import org.mtgdb.db.repository.IDeckMagicCardRepository;
import org.mtgdb.db.repository.IEditionRepository;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.db.repository.IPhysicalCardRepository;
import org.mtgdb.db.repository.MagicCardRepository;
import org.mtgdb.db.repository.PhysicalCardRepository;

/**
 * @author Sandro Orlando
 */
public final class ServiceManager {

  public static final ServiceManager instance = new ServiceManager();
  private Injector injector;

  private ServiceManager() {
    createGuice();
  }

  public static <T> T get(final Class<T> type) {
    return instance.doGet(type);
  }

  private void createGuice() {
    this.injector = Guice.createInjector(new AbstractModule() {
      @Override
      protected void configure() {
        final DatabaseConnection connection = new DatabaseConnection();
        bind(IDatabaseConnection.class).toInstance(connection);
        bind(IMagicCardRepository.class).toInstance(new MagicCardRepository(connection));
        bind(IEditionRepository.class).toInstance(new EditionRepository(connection));
        bind(IContainerRepository.class).toInstance(new ContainerRepository(connection));
        bind(IPhysicalCardRepository.class).toInstance(new PhysicalCardRepository(connection));
        bind(ICardMemoryRepository.class).toInstance(new CardMemoryRepository(connection));
        bind(IDeckMagicCardRepository.class).toInstance(new DeckMagicCardRepository(connection));
      }
    });
  }

  public <T> T doGet(final Class<T> type) {
    return injector.getInstance(type);
  }


}
