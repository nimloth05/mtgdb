package org.mtgdb.ui.util.components.label;

import org.mtgdb.util.dispose.IDisposable;

import javax.swing.*;

/**
 * Date: 24.12.10
 * Time: 13:31
 *
 * @author Sandro Orlando
 */
public final class LabelModelAdapter {

  public static IDisposable connect(final JLabel label, final ILabelModel model) {
    label.setText(model.getText());
    label.setIcon(model.getIcon());
    return model.addListener(new ILabelModelListener() {

      @Override
      public void changed(final String newText) {
        label.setText(newText);
      }

      @Override
      public void changed(final Icon icon) {
        label.setIcon(icon);
        label.getParent().doLayout();
        label.getParent().repaint();
      }
    });
  }
}
