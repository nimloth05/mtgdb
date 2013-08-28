package org.mtgdb.ui.util.dialog.properties.list;

import org.mtgdb.model.Container;
import org.mtgdb.ui.util.dialog.properties.IProperty;

import javax.swing.*;

/**
 *
 */
public interface IListProperty extends IProperty {

  public ListPropertyModel getModel();

  public IDoubleClickHandler getDoubleClickHandler();

  ListCellRenderer<Container> getCellRenderer();
}
