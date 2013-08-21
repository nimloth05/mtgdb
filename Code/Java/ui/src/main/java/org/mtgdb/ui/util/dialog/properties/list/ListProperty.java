package org.mtgdb.ui.util.dialog.properties.list;

import org.mtgdb.ui.util.dialog.properties.AbstractProperty;
import org.mtgdb.ui.util.dialog.properties.IPropertyVisitor;

/**
 * Property which represents a list.
 */
public final class ListProperty extends AbstractProperty implements IListProperty {

  private ListPropertyModel fModel;
  private IDoubleClickHandler fHandler;

  public ListProperty(final String id, final ListPropertyModel model) {
    super(id);
    fModel = model;
  }

  @Override
  public ListPropertyModel getModel() {
    return fModel;
  }

  @Override
  public void visit(final IPropertyVisitor visitor) {
    visitor.accept(this);
  }

  public ListProperty setDoubleClickHandler(final IDoubleClickHandler handler) {
    fHandler = handler;
    return this;
  }

  public IDoubleClickHandler getDoubleClickHandler() {
    return fHandler;
  }
}
