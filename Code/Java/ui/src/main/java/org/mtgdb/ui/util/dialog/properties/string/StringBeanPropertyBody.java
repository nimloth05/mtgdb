package org.mtgdb.ui.util.dialog.properties.string;

import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;
import org.mtgdb.ui.util.models.DocumentHelper;
import org.mtgdb.util.ReflectionUtil;

import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import static org.mtgdb.util.ReflectionUtil.*;

/**
 * String PropertyBody which uses reflection.
 */
public final class StringBeanPropertyBody implements IStringModelBody {

  private Object fModel;
  private String fName;

  public StringBeanPropertyBody(final Object model, final String name) {
    fModel = model;
    fName = name;
  }

  @Override
  public Document initialize(final IPropertyModelContext context) {
    Document document = new PlainDocument();
    final String value = callGetter(fModel, "doGet" + fName).toString();
    DocumentHelper.setText(document, value);
    return document;
  }

  @Override
  public void ok(final Document document) {
    ReflectionUtil.callSetter(fModel, "set" + fName, DocumentHelper.getText(document));
  }
}
