package org.mtgdb.ui.util.dialog.properties.selection;

import org.mtgdb.ui.util.dialog.properties.IPropertyModel;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;

import javax.swing.*;

/**
 *
 */
public class SelectionPropertyModel implements IPropertyModel {

  private IPropertyModelContext fContext;
  private ISelectionPropertyModelBody fBody;
  private ListSelectionModel fSelectionModel;
  private ListModel fListModel;

  public SelectionPropertyModel(final IPropertyModelContext context, final ISelectionPropertyModelBody body) {
    fContext = context;
    fBody = body;
    fSelectionModel = new DefaultListSelectionModel();
    fSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    fListModel = fBody.initialize(fContext);

    for (int i = 0; i < fListModel.getSize(); ++i) {
      Object object = fListModel.getElementAt(i);
      if (object.equals(fBody.getInitialSelectedElement())) {
        fSelectionModel.setSelectionInterval(i, i);
      }
    }
  }

  public ListSelectionModel getSelectionModel() {
    return fSelectionModel;
  }

  public ListModel getListModel() {
    return fListModel;
  }

  @Override
  public void ok() {
    final int index = fSelectionModel.getMinSelectionIndex();
    fBody.ok(fListModel.getElementAt(index));
  }
}
