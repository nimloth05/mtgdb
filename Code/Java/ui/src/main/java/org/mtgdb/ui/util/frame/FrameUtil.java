package org.mtgdb.ui.util.frame;

import org.mtgdb.ui.util.components.ButtonUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public final class FrameUtil {

  public static void close(JDialog frame) {
    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
  }

  public static void close(final JFrame frame) {
    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
  }

  /**
   * Connects the given button with the escape key. The escape key will simulate a click on the button.
   *
   * @param cancelButton
   */
  public static void connectEscapeWithButton(final JButton cancelButton) {
    connectEscapeWithButton(cancelButton, new AbstractAction() {

      public void actionPerformed(ActionEvent e) {
        ButtonUtil.click(cancelButton.getModel());
      }
    });
  }

  public static void connectEscapeWithButton(final JButton cancelButton, Action cancelKeyAction) {
    final InputMap inputMap = cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    final ActionMap actionMap = cancelButton.getActionMap();

    final KeyStroke cancelKeyStroke = KeyStroke.getKeyStroke("ESCAPE"); //$NON-NLS-1$
    final String actionID = "cancel"; //$NON-NLS-1$

    inputMap.put(cancelKeyStroke, actionID);
    actionMap.put(actionID, cancelKeyAction);
    cancelButton.setActionCommand(actionID);
  }

  public static Window getFrameByTitle(String string) {
    for (Window window : Window.getWindows()) {
      if (window instanceof Dialog) {
        Dialog dialog = (Dialog) window;
        if (dialog.getTitle().equalsIgnoreCase(string)) return dialog;
      }
      if (window instanceof Frame) {
        Frame frame = (Frame) window;
        if (frame.getTitle().equals(string)) return frame;
      }
    }
    return null;
  }

  private FrameUtil() {
  }

}
