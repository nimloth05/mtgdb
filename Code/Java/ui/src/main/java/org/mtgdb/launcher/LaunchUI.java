package org.mtgdb.launcher;

import org.mtgdb.ui.MainWindow;
import org.mtgdb.ui.util.NimbusUtil;

import javax.swing.*;

/**
 * @author Sandro Orlando
 */
public final class LaunchUI {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        NimbusUtil.setNimbus();
        MainWindow.createAndShow();
      }
    });
  }

}
