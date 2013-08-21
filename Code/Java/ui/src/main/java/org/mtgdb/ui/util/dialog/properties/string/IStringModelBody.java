package org.mtgdb.ui.util.dialog.properties.string;

import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;

import javax.swing.text.Document;

/**
 * @author Sandro
 */
public interface IStringModelBody {

  public Document initialize(final IPropertyModelContext context);

  public void ok(Document document);
}
