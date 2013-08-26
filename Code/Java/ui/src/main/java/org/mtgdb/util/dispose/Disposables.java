package org.mtgdb.util.dispose;

import org.mtgdb.util.assertion.Assert;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * Composite Implementierung des <tt>{@link IDisposable}</tt> Interfaces.
 * 
 * @author sandro
 *
 */
public class Disposables implements IDisposable {
  
  /**
   * Liste mit den eingetragenen Disposables.
   */
  private final List<IDisposable> fDisposables = new LinkedList<IDisposable>();
  
  public<T extends IDisposable> T add(final T disposable) {
    if (disposable == null) throw new IllegalArgumentException("disposable");
    fDisposables.add(disposable);
    return disposable;
  }

  /**
   * Ruft auf allen enthaltenen Dispose-Objekten dipose auf. Danach wird die Liste geleert.
   * 
   */
  public void dispose() {
    final Iterator<IDisposable> iterator = fDisposables.iterator();
    while (iterator.hasNext()) {
      disposeObject(iterator.next());
    }
    fDisposables.clear();
  }

  private void disposeObject(final IDisposable disposable) {
    try {
      disposable.dispose();
    }
    catch (final Throwable t) {
      Assert.log(t);
    }
  }

}
