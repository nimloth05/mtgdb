package org.mtgdb.db.repository;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.IDatabaseConnectionListener;

import java.sql.SQLException;
import java.util.List;

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
          dao.setObjectCache(true);
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void closing(final ConnectionSource connection) {
      }
    });
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
