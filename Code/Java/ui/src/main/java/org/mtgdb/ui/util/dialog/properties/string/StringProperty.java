package org.mtgdb.ui.util.dialog.properties.string;

import org.mtgdb.ui.util.dialog.properties.AbstractProperty;
import org.mtgdb.ui.util.dialog.properties.IPropertyVisitor;

import javax.swing.*;
import javax.swing.text.Document;


/**
 * @author Sandro
 */
public final class StringProperty extends AbstractProperty implements IStringProperty {

  private StringModel fModel;
  private JFormattedTextField.AbstractFormatter fFormatter;

  public StringProperty(final String id, final StringModel model) {
    super(id);
    fModel = model;
  }

  public StringModel getModel() {
    return fModel;
  }

  public void visit(final IPropertyVisitor visitor) {
    visitor.accept(this);
  }

  public Document getComponentModel() {
    return fModel.getDocument();
  }

  public StringProperty setFormatter(final JFormattedTextField.AbstractFormatter formatter) {
    fFormatter = formatter;
    return this;
  }

  public JFormattedTextField.AbstractFormatter getFormatter() {
    return fFormatter;
  }
}