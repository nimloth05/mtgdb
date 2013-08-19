package org.mtgdb.ui.util.frame;

import org.mtgdb.util.Constants;

import javax.swing.*;
import java.awt.*;

public class FrameFactory {

  private static Window sFrame = null;

  /**
   * @param width
   * @param height
   * @return
   */
  public static JDialog createCenteredDialog(Window parent, int width, int height) {
    return createCenteredDialog(parent, Constants.EMPTY, width, height);
  }

  public static JDialog createCenteredDialog(Window parent, String title, Dimension size) {
    return createCenteredDialog(parent, title, size.width, size.height);
  }

  public static JDialog createCenteredDialog(Window parent, String title, int width, int height) {
    final JDialog dialog = new JDialog(parent, Dialog.DEFAULT_MODALITY_TYPE);
    dialog.setTitle(title);
    dialog.setSize(width, height);
    dialog.setTitle(title);
    dialog.setLocation(getCenterPosition(dialog.getSize()));
    return dialog;
  }

  public static JFrame createCenteredFrame(final int width, final int height) {
    return createCenteredFrame(Constants.EMPTY, width, height);
  }

  public static JFrame createCenteredFrame(String title, final int width, final int height) {
    final JFrame frame = new JFrame(title);
    setSizeAndLocation(width, height, frame);
    return frame;
  }

  public static Window getMainFrame() {
    return sFrame;
  }

  public static void setMainFrame(Window frame) {
    sFrame = frame;
  }

  /**
   * Platziert das Frame in der Mitte des Bildschrimes.
   * <p/>
   * HINWEIS: Die Grösse des Frames muss bereits gesetzt sein.
   *
   * @param frame
   */
  private static void centerFrame(final JFrame frame) {
    frame.setLocation(getCenterPosition(frame.getSize()));
  }

  private static Point getCenterPosition(final Dimension frameSize) {
    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return new Point((screenSize.width - (int) frameSize.getWidth()) / 2, (screenSize.height - (int) frameSize.getHeight()) / 2);
  }

  private static void setSizeAndLocation(final int width, final int height, final JFrame frame) {
    frame.setSize(width, height);
    centerFrame(frame);
  }

}
