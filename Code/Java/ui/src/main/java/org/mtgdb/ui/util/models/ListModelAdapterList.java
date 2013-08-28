package org.mtgdb.ui.util.models;

import javax.swing.*;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class ListModelAdapterList<T> extends AbstractListModel<T>{

  private final List<T> data;

  public ListModelAdapterList(final List<T> data) {
    if (data == null) throw new IllegalArgumentException("list is null");
    this.data = data;
  }

  @Override
  public int getSize() {
    return data.size();
  }

  @Override
  public T getElementAt(final int index) {
    return data.get(index);
  }

  public void add(final T container) {
    data.add(container);
    fireIntervalAdded(this, data.size()-1, data.size()-1);
  }

  public T remove(final int index) {
    final T remove = data.remove(index);
    fireIntervalRemoved(this, index, index);
    return remove;
  }

  public void modelChanged(final T t) {
    fireContentsChanged(this, data.indexOf(t), data.indexOf(t));
  }
}
