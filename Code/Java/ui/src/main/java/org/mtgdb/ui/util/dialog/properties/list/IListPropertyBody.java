package org.mtgdb.ui.util.dialog.properties.list;

import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;

import javax.swing.*;

/**
 */
public interface IListPropertyBody {

  public ListModel initializeList(IPropertyModelContext context);

  public void addElement(IPropertyModelContext context, final ListModel listModel);

  public void ok(ListModel listModel);

  public void removeElement(IPropertyModelContext context, ListModel listModel, int index);
}
