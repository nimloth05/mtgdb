package org.mtgdb.util;

import org.mtgdb.util.dispose.IDisposable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ListenerList<T> implements Iterable<T> {
  
  private final List<T> fListeners = new ArrayList<T>();
  
  public IDisposable add(final T listener) {
    fListeners.add(listener);
    
    return new IDisposable() {

      public void dispose() {
        fListeners.remove(listener);
      }
      
    };
  }
  
  public void clear() {
    fListeners.clear();
  }

  public boolean isEmpty() {
    return fListeners.isEmpty();
  }

  public Iterator<T> iterator() {
    return getListeners().iterator();
  }

  public void reset() {
    fListeners.clear();
  }

  public int size() {
    return fListeners.size();
  }
  
  @Override
  public String toString() {
    return fListeners.toString();
  }

  /**
   * @return A copy of the listener-list.
   * Very important to allow modifications during notifications. 
   */
  private List<T> getListeners() {
    return new ArrayList<T>(fListeners);
  }
  
  
}
