package org.mtgdb.ui.util.dialog.properties.string;

import org.mtgdb.ui.util.dialog.properties.IPropertyModel;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;

import javax.swing.text.Document;

/**
 * @author Sandro
 */
public final class StringModel implements IPropertyModel {

  /**
   * Document, dient als Kommunikation mit dem Control.
   */
  private Document fDocument;
  private IPropertyModelContext fContext;
  private IStringModelBody fBody;

  public StringModel(IStringModelBody body, IPropertyModelContext context) {
    fBody = body;
    fContext = context;
    fDocument = fBody.initialize(fContext);
  }

  @Override
  public final void ok() {
    fBody.ok(fDocument);
  }

  public final Document getDocument() {
    return fDocument;
  }

}