package org.mtgdb.db.repository;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.ObjectFactory;
import com.j256.ormlite.table.TableUtils;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.IDatabaseConnectionListener;
import org.mtgdb.services.ServiceManager;

import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Sandro Orlando
 */
public abstract class AbstractRepository<T, ID> implements IRepository<T> {

  protected Dao<T, ID> dao;

  @Inject
  public AbstractRepository(final IDatabaseConnection connection) {
    connection.addListener(new IDatabaseConnectionListener() {
      @Override
      public void opened(final ConnectionSource connection) {
        try {
          TableUtils.createTableIfNotExists(connection, getClassLiteral());
          dao = DaoManager.createDao(connection, getClassLiteral());
          dao.setObjectFactory(createGuiceObjectFactory());
          dao.setObjectCache(true);
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }

      private ObjectFactory<T> createGuiceObjectFactory() {
        return new ObjectFactory<T>() {
          @Override
          public T createObject(final Constructor<T> construcor, final Class<T> dataClass) throws SQLException {
            return ServiceManager.get(dataClass);
          }
        };
      }

      @Override
      public void closing(final ConnectionSource connection) {
      }
    });
  }

  @Override
  public final void saveAll(final Collection<T> objects) {
    try {
      dao.callBatchTasks(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
          for (T card : objects) {
            setId(card);
            dao.create(card);
          }
          return null;
        }
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected void setId(final T obj) {

  }

  @Override
  public void save(final T container) {
    try {
      dao.create(container);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  protected abstract Class<T> getClassLiteral();

  @Override
  public List<T> getAll() {
    try {
      return dao.queryForAll();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
