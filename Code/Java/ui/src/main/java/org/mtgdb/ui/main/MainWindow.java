package org.mtgdb.ui.main;

import net.miginfocom.swing.MigLayout;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.card.MagicCardPanel;
import org.mtgdb.ui.util.frame.FrameFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    MainWindow window = new MainWindow(ServiceManager.get(MainModel.class));
    window.show();
  }

  private void createContentArea() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout(""));

    panel.add(new JLabel("Your Library:"), "wrap");
    panel.add(new MagicCardPanel(model.getPanelModel()).getPanel());
    model.getPanelModel().showAll();

    frame.getContentPane().add(panel, BorderLayout.CENTER);
  }

  private void createToolbar() {
    JToolBar toolBar = new JToolBar();
    toolBar.setFloatable(false);
    toolBar.add(model.getGrabberAction());
    toolBar.add(model.getShowContainersAction());
    toolBar.add(model.getShowSearchAction());
    frame.getContentPane().add(toolBar, BorderLayout.NORTH);
  }

  public void show() {
    frame = FrameFactory.createCenteredFrame("MTG Administration", 1000, 700);
    FrameFactory.setMainFrame(frame);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        model.closeDB();
      }
    });
    frame.getContentPane().setLayout(new BorderLayout());
    createToolbar();
    createContentArea();
    frame.setVisible(true);

  }
}
