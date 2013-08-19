package org.mtgdb.ui;

import org.mtgdb.grabber.GrabberJsoup;
import org.mtgdb.ui.util.frame.progress.IProgressMonitor;
import org.mtgdb.ui.util.frame.progress.IProgressRunnable;
import org.mtgdb.ui.util.frame.progress.ProgressDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;

/**
 * @author Sandro Orlando
 */
public final class MainModel {

  private Action grabberAction = new GrabberAction();

  public Action getGrabberAction() {
    return grabberAction;
  }

  public TableModel getLibraryModel() {
    return new DefaultTableModel();
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
          final String url = "http://magiccards.info/m14/en.html";
          GrabberJsoup grabberJsoup = new GrabberJsoup(url);
          grabberJsoup.grabEdition(url, monitor);
        }
      });
      dialog.setMin(0);
      dialog.setMax(249);
      dialog.start();
    }
  }
}
