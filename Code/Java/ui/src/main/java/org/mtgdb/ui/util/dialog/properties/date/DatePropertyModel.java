package org.mtgdb.ui.util.dialog.properties.date;

import org.mtgdb.ui.util.dialog.properties.IPropertyModel;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;

import java.util.Date;

/**
 * Model for the DateProperty
 */
public final class DatePropertyModel implements IPropertyModel {

  private IPropertyModelContext fContext;
  private IDatePropertyModelBody fBody;
  private Date fDate;

  public DatePropertyModel(final IPropertyModelContext context, final IDatePropertyModelBody body) {
    fContext = context;
    fBody = body;
    fDate = fBody.initialize(fContext);
  }

  @Override
  public void ok() {
    fBody.ok(fDate);
  }

  public Date getDate() {
    return fDate;
  }

  public void setDate(final Date date) {
    fDate = date;
  }

}
