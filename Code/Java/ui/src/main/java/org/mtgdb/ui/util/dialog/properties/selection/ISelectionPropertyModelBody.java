package org.mtgdb.ui.util.dialog.properties.selection;

import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;

import javax.swing.*;

/**
 *
 */
public interface ISelectionPropertyModelBody<T> {

  public ListModel initialize(IPropertyModelContext context);

  public Object getInitialSelectedElement();

  public void ok(T object);

}
