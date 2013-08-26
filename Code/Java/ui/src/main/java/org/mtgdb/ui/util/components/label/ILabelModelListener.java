package org.mtgdb.ui.util.components.label;

import javax.swing.*;

/**
 * Date: 24.12.10
 * Time: 13:32
 *
 * @author Sandro Orlando
 */
public interface ILabelModelListener {

  public void changed(String newText);

  public void changed(Icon icon);

}
