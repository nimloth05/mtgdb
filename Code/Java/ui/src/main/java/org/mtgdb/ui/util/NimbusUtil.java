package org.mtgdb.ui.util;

import javax.swing.*;

/**
 * @author Sandro
 */
public final class NimbusUtil {

  private NimbusUtil() {
  }

  public static void setNimbus() {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if (info.getClassName().contains("Nimbus")) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
