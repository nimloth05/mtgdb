package org.mtgdb.ui.util.dialog.properties.date;

import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;

import java.util.Date;

/**
 *
 */
public interface IDatePropertyModelBody {

  public Date initialize(IPropertyModelContext context);

  public void ok(final Date date);
}
