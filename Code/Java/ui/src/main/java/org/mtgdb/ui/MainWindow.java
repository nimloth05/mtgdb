package org.mtgdb.ui;

import org.mtgdb.ui.util.frame.FrameFactory;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sandro Orlando
 */
public final class MainWindow {

  private JFrame frame;
  private MainModel model;

  public MainWindow(final MainModel model) {
    this.model = model;

  }

  public static void createAndShow() {
    MainWindow window = new MainWindow(new MainModel());
    window.show();
  }

  private void createContentArea() {
    JPanel panel = new JPanel();
  }

  private void createToolbar() {
    JToolBar toolBar = new JToolBar();
    toolBar.setFloatable(false);
    toolBar.add(model.getGrabberAction());
    frame.getContentPane().add(toolBar, BorderLayout.NORTH);
  }

  public void show() {
    frame = FrameFactory.createCenteredFrame("MTG AdministratioN", 640, 480);
    frame.getContentPane().setLayout(new BorderLayout());
    createToolbar();
    createContentArea();
    frame.setVisible(true);
  }
}
