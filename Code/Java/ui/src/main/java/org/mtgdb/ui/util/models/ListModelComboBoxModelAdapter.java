package org.mtgdb.ui.util.models;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public final class ListModelComboBoxModelAdapter implements ComboBoxModel {

  private ListModel fListModel;
  private ListSelectionModel fSelectionModel;
  private List<ListDataListener> fListeners = new ArrayList<ListDataListener>();

  public ListModelComboBoxModelAdapter(final ListModel listModel, final ListSelectionModel selectionModel) {
    if (selectionModel.getSelectionMode() != ListSelectionModel.SINGLE_SELECTION) throw new IllegalArgumentException("this class only supports single selection");
    fListModel = listModel;
    fSelectionModel = selectionModel;
    fSelectionModel.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(final ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) return;
        notifyListDataListeners();
      }
    });
  }

  @Override
  public int getSize() {
    return fListModel.getSize();
  }

  @Override
  public Object getElementAt(final int index) {
    return fListModel.getElementAt(index);
  }

  @Override
  public void addListDataListener(final ListDataListener l) {
    fListModel.addListDataListener(l);
    fListeners.add(l);
  }

  @Override
  public void removeListDataListener(final ListDataListener l) {
    fListModel.removeListDataListener(l);
    fListeners.remove(l);
  }

  @Override
  public void setSelectedItem(final Object anItem) {
    for(int i = 0; i < fListModel.getSize(); ++i) {
      Object element = fListModel.getElementAt(i);
      if (element.equals(anItem)) {
        fSelectionModel.setSelectionInterval(i, i);
        return;
      }
    }
  }

  private void notifyListDataListeners() {
    for(int i = fListeners.size()-1; i >= 0; --i) {
      ListDataListener listener = fListeners.get(i);
      listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED,  -1, -1));
    }
  }

  @Override
  public Object getSelectedItem() {
    return fListModel.getElementAt(fSelectionModel.getMinSelectionIndex());
  }
}
