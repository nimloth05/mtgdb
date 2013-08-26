package org.mtgdb.ui.util.components.label;

import org.mtgdb.util.dispose.IDisposable;

import javax.swing.*;

/**
 * Date: 24.12.10
 * Time: 13:32
 *
 * @author Sandro Orlando
 */
public interface ILabelModel {

  public IDisposable addListener(ILabelModelListener listener);

  public String getText();

  public Icon getIcon();
}
