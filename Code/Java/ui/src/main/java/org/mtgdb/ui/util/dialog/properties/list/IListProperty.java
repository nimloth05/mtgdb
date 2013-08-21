package org.mtgdb.ui.util.dialog.properties.list;

import org.mtgdb.ui.util.dialog.properties.IProperty;

/**
 *
 */
public interface IListProperty extends IProperty {

  public ListPropertyModel getModel();

  public IDoubleClickHandler getDoubleClickHandler();

}
