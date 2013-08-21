package org.mtgdb.ui.util.dialog.properties.date;

import org.mtgdb.ui.util.dialog.properties.IProperty;

/**
 * Property, which represents a date.
 */
public interface IDateProperty extends IProperty {

  public DatePropertyModel getModel();

  public String getFormat();
}
