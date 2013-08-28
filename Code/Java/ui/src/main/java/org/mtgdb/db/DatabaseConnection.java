package org.mtgdb.db;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;
import org.mtgdb.util.ListenerList;
import org.mtgdb.util.assertion.Assert;
import org.mtgdb.util.dispose.IDisposable;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * @author Sandro Orlando
 */
public final class DatabaseConnection implements IDatabaseConnection {

  private Path path;
  private ConnectionSource source;
  private ListenerList<IDatabaseConnectionListener> listeners = new ListenerList<>();

  public DatabaseConnection() {
  }

  private void open(final Path path) {
    this.path = path;
    try {
      Class.forName("org.h2.Driver");
      final String url = "jdbc:h2:" + path.toAbsolutePath().toFile();
      source = new JdbcConnectionSource(url);
      notifyDBOpened();
    } catch (SQLException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private void notifyDBClosing() {
    for (IDatabaseConnectionListener listener : listeners) {
      listener.closing(source);
    }
  }

  private void notifyDBOpened() {
    for (IDatabaseConnectionListener listener : listeners) {
      listener.opened(source);
    }
  }

  @Override
  public void openDB() {
    open(FileSystems.getDefault().getPath("data.db"));
  }

  @Override
  public void execute(final ITransactionRunnable runnable) {
    try {
      TransactionManager.callInTransaction(source, new Callable<Object>() {
        @Override
        public Object call() throws Exception {
          runnable.run();
          return null;
        }
      });
    } catch (Exception e) {
      throw new RuntimeException("exception during transaction", e);
    }
  }

  @Override
  public IDisposable addListener(final IDatabaseConnectionListener listener) {
    return listeners.add(listener);
  }

  @Override
  public void closeDB() {
    try {
      notifyDBClosing();
      source.close();
    } catch (SQLException e) {
      Assert.log(e);
    }
  }

  private class TransactionToken implements ITransactionToken {

  }
}
