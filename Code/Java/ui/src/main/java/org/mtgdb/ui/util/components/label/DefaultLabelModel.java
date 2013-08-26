package org.mtgdb.ui.util.components.label;

import org.mtgdb.util.ListenerList;
import org.mtgdb.util.dispose.IDisposable;

import javax.swing.*;

/**
 * Date: 24.12.10
 * Time: 13:35
 *
 * @author Sandro Orlando
 */
public class DefaultLabelModel implements ILabelModel {

  private ListenerList<ILabelModelListener> listeners = new ListenerList<ILabelModelListener>();
  private String text = "";
  private Icon icon = null;

  @Override
  public IDisposable addListener(final ILabelModelListener listener) {
    return listeners.add(listener);
  }

  public void setText(final String text) {
    if (this.text.equals(text)) return;
    this.text = text;
    for(ILabelModelListener listener: listeners) {
      listener.changed(text);
    }
  }

  public void setIcon(final Icon icon) {
    this.icon = icon;
    for (ILabelModelListener listener : listeners) {
      listener.changed(icon);
    }
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public Icon getIcon() {
    return icon;
  }
}
