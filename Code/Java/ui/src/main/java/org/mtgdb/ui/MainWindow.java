package org.mtgdb.ui;

import net.miginfocom.swing.MigLayout;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.util.frame.FrameFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

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
    MainWindow window = new MainWindow(ServiceManager.instance.get(MainModel.class));
    window.show();
  }

  private void createContentArea() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout());
    panel.add(new JLabel("Your Library:"), "wrap");
    final JTable table = new JTable();
    final JScrollPane pane = new JScrollPane(table);
    table.setModel(model.getLibraryModel());
    final JLabel imgLbl = createImageLabel("http://magiccards.info/scans/en/pr/133.jpg");

    panel.add(imgLbl, "spany 2");
    panel.add(table.getTableHeader(), "wrap");
    panel.add(pane, "width :100%:");
    frame.getContentPane().add(panel, BorderLayout.CENTER);
  }

  private JLabel createImageLabel(String urlString) {
    Image scan = null;
    try {
      URL url = new URL(urlString);
      scan = ImageIO.read(url);
    } catch (IOException e) {
    }
    return new JLabel(new ImageIcon(scan));
  }

  private void createToolbar() {
    JToolBar toolBar = new JToolBar();
    toolBar.setFloatable(false);
    toolBar.add(model.getGrabberAction());
    toolBar.add(model.getAddContainerAction());
    frame.getContentPane().add(toolBar, BorderLayout.NORTH);
  }

  public void show() {
    frame = FrameFactory.createCenteredFrame("MTG Administration", 800, 600);
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
