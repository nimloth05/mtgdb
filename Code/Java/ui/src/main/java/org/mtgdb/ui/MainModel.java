package org.mtgdb.ui;

import org.mtgdb.grabber.GrabberJsoup;
import org.mtgdb.ui.util.frame.progress.IProgressMonitor;
import org.mtgdb.ui.util.frame.progress.IProgressRunnable;
import org.mtgdb.ui.util.frame.progress.ProgressDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Sandro Orlando
 */
public final class MainModel {

  private Action grabberAction = new GrabberAction();

  public Action getGrabberAction() {
    return grabberAction;
  }
  private static class GrabberAction extends AbstractAction {

    public GrabberAction() {
      super();
      putValue(Action.NAME, "Grabb DB");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      ProgressDialog dialog = ProgressDialog.create(new IProgressRunnable() {
        @Override
        public void done() {
        }

        @Override
        public void run(final IProgressMonitor monitor) throws Exception {
          monitor.setMessage("Grabbing DB...");
          GrabberJsoup grabberJsoup = new GrabberJsoup("http://magiccards.info/m14/en.html");
          grabberJsoup.grab(monitor);
        }
      });
      dialog.setMin(0);
      dialog.setMax(249);
      dialog.start();
    }
  }
}
