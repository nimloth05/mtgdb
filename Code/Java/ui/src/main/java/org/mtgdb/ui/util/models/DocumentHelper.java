package org.mtgdb.ui.util.models;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

public final class DocumentHelper {
  
  public static String getText(final Document document) {
    try {
      return document.getText(0, document.getLength());
    }
    catch (final BadLocationException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static void setText(final Document document, final String content) {
    try {
      document.insertString(0, content, null);
    }
    catch (final BadLocationException e) {
      throw new RuntimeException(e);
    }
  }

  public static void clear(final Document document) {
    try {
      document.remove(0, document.getLength());
    }
    catch(final BadLocationException e) {
      throw new RuntimeException(e);
    }
  }

  public static void append(final Document document, final String text, final AttributeSet set) {
    try {
      document.insertString(document.getLength(), text, set);
    }
    catch (BadLocationException e) {
      throw new RuntimeException(e);
    }
  }

  public static AttributeSet foregroundColor(AttributeSet old, Color color) {
    StyleContext sc = StyleContext.getDefaultStyleContext();
    return sc.addAttribute(old, StyleConstants.Foreground, color);
  }

  public static AttributeSet foregroundColor(Color color) {
    return foregroundColor(SimpleAttributeSet.EMPTY, color);
  }
  
  private DocumentHelper() {}

}
