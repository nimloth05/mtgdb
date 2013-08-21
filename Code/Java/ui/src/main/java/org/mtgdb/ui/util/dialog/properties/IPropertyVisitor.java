package org.mtgdb.ui.util.dialog.properties;

import org.mtgdb.ui.util.dialog.properties.date.IDateProperty;
import org.mtgdb.ui.util.dialog.properties.list.IListProperty;
import org.mtgdb.ui.util.dialog.properties.selection.ISelectionProperty;
import org.mtgdb.ui.util.dialog.properties.string.IStringProperty;

/**
 * @author Sandro
 */
public interface IPropertyVisitor {

  public void accept(IStringProperty property);

  public void accept(IListProperty property);

  public void accept(IDateProperty dateProperty);

  public void accept(ISelectionProperty selectionProperty);
}