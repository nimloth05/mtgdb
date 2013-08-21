package org.mtgdb.ui.util.dialog.properties.list;

import org.mtgdb.ui.util.dialog.properties.IPropertyModel;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 */
public final class ListPropertyModel implements IPropertyModel {

  private class DefaultRemoveAction extends AbstractAction {

    public DefaultRemoveAction() {
      super("-");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      final int index = fListSelectionModel.getMinSelectionIndex();
      if (index < 0) return;
      fBody.removeElement(fContext, fListModel, index);
    }
  }

  private class DefaultAddAction extends AbstractAction {

    public DefaultAddAction() {
      super("+");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      fBody.addElement(fContext, fListModel);
    }
  }

  /**
   * Document, dient als Kommunikation mit dem Control.
   */
  private ListModel fListModel;
  private DefaultListSelectionModel fListSelectionModel;
  private Action fAddAction;
  private Action fRemoveAction;
  private IPropertyModelContext fContext;
  private IListPropertyBody fBody;

  public ListPropertyModel(IListPropertyBody body, IPropertyModelContext context) {
    fListSelectionModel = new DefaultListSelectionModel();
    fListSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    fBody = body;
    fContext = context;
    fListModel = fBody.initializeList(fContext);
    fAddAction = new DefaultAddAction();
    fRemoveAction = new DefaultRemoveAction();
  }

  @Override
  public final void ok() {
    fBody.ok(fListModel);
  }

  public ListModel getListModel() {
    return fListModel;
  }

  public Action getAddAction() {
    return fAddAction;
  }

  public Action getRemoveAction() {
    return fRemoveAction;
  }

  public DefaultListSelectionModel getListSelectionModel() {
    return fListSelectionModel;
  }
}
