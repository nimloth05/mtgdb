package org.mtgdb.ui.util.dialog.properties.string;

import org.mtgdb.ui.util.dialog.properties.IProperty;

import javax.swing.*;
import javax.swing.text.Document;


/**
 * @author Sandro
 */
public interface IStringProperty extends IProperty {

  public StringModel getModel();

  public Document getComponentModel();

  /**
   * @return the formatter or null, if no formatter is set.
   */
  public JFormattedTextField.AbstractFormatter getFormatter();
}