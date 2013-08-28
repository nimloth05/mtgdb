package org.mtgdb.ui.util.dialog.properties.list;

import org.mtgdb.model.Container;
import org.mtgdb.ui.util.dialog.properties.AbstractProperty;
import org.mtgdb.ui.util.dialog.properties.IPropertyVisitor;

import javax.swing.*;

/**
 * Property which represents a list.
 */
public final class ListProperty extends AbstractProperty implements IListProperty {

  private ListPropertyModel fModel;
  private IDoubleClickHandler fHandler;
  private ListCellRenderer<Container> cellRenderer;

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

  public ListProperty setCellRenderer(final ListCellRenderer<Container> cellRenderer) {
    this.cellRenderer = cellRenderer;
    return this;
  }

  @Override
  public ListCellRenderer<Container> getCellRenderer() {
    return cellRenderer;
  }
}
