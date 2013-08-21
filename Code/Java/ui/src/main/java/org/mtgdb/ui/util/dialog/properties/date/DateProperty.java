package org.mtgdb.ui.util.dialog.properties.date;

import org.mtgdb.ui.util.dialog.properties.AbstractProperty;
import org.mtgdb.ui.util.dialog.properties.IPropertyVisitor;


public final class DateProperty extends AbstractProperty implements IDateProperty {

  private String fFormat;
  private DatePropertyModel fModel;

  public DateProperty(final String id, final DatePropertyModel model) {
    super(id);
    fModel = model;
  }

  public void setFormat(final String format) {
    fFormat = format;
  }

  public String getFormat() {
    return fFormat;
  }

  @Override
  public DatePropertyModel getModel() {
    return fModel;
  }

  @Override
  public void visit(final IPropertyVisitor visitor) {
    visitor.accept(this);
  }
}
