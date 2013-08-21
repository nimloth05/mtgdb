package org.mtgdb.ui.util.dialog.properties.selection;

import org.mtgdb.ui.util.dialog.properties.AbstractProperty;
import org.mtgdb.ui.util.dialog.properties.IPropertyVisitor;

/**
 *
 */
public final class SelectionProperty extends AbstractProperty implements ISelectionProperty {

  private SelectionPropertyModel fModel;

  public SelectionProperty(final String id, final SelectionPropertyModel model) {
    super(id);
    fModel = model;
  }

  @Override
  public SelectionPropertyModel getModel() {
    return fModel;
  }

  @Override
  public void visit(final IPropertyVisitor visitor) {
    visitor.accept(this);
  }
}
