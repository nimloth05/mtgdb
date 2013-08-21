package org.mtgdb.ui.util.models;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.lang.annotation.Documented;

/**
 * @author Sandro
 */
public class DocumentChangeAdapter implements DocumentListener {

  @Override
  public void insertUpdate(final DocumentEvent e) {
    change(e);
  }

  @Override
  public void removeUpdate(final DocumentEvent e) {
    change(e);
  }

  @Override
  public void changedUpdate(final DocumentEvent e) {
    change(e);
  }

  protected void change(DocumentEvent e) {}
}
