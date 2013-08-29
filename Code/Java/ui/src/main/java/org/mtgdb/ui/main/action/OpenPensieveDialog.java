package org.mtgdb.ui.main.action;

import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.pensieve.PensieveWindow;
import org.mtgdb.ui.pensieve.PensieveWindowModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Sandro Orlando
 */
public final class OpenPensieveDialog extends AbstractAction{

  public OpenPensieveDialog() {
    putValue(NAME, "Open Pensieve");
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    PensieveWindow window = new PensieveWindow(ServiceManager.get(PensieveWindowModel.class));
    window.show();
  }
}
