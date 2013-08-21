package org.mtgdb.ui.util.components;

import org.mtgdb.ui.util.ImageLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Date: 24.12.10
 * Time: 14:27
 *
 * @author Sandro Orlando
 */
public final class ButtonUtil {

  private ButtonUtil() {
  }

  public static void click(ButtonModel model) {
    model.setArmed(true);
    model.setPressed(true);
    model.setPressed(false);
    model.setArmed(false);
  }

  public static Action adaptToAction(final ButtonModel model) {
    return new AbstractAction() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        click(model);
      }
    };
  }

  public static JButton addToolbar(final JToolBar toolBar, final ButtonModel model, final String label, final String icon) {
    JButton button = toolBar.add(adaptToAction(model));
    button.setText(label);
    button.setIcon(ImageLoader.loadAsIcon(icon));
    return button;
  }
}
