package org.mtgdb.ui;

import org.mtgdb.db.DatabaseAccess;
import org.mtgdb.db.DatabaseConnection;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.grabber.GrabberJsoup;
import org.mtgdb.grabber.IGrabberListener;
import org.mtgdb.model.CardDescription;
import org.mtgdb.ui.util.frame.progress.IProgressMonitor;
import org.mtgdb.ui.util.frame.progress.IProgressRunnable;
import org.mtgdb.ui.util.frame.progress.ProgressDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class MainModel {

  private Action grabberAction = new GrabberAction();
  private final DatabaseAccess dbAccess;

  public MainModel() {
    IDatabaseConnection connection = DatabaseConnection.create();
    dbAccess = new DatabaseAccess(connection);
  }
  public void closeDB(){
    dbAccess.closeDB();
  }

  public Action getGrabberAction() {
    return grabberAction;
  }

  public TableModel getLibraryModel() {
    return new DefaultTableModel();
  }

  private class GrabberAction extends AbstractAction {

    public GrabberAction() {
      super();
      putValue(Action.NAME, "Grabb DB");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      ProgressDialog dialog = ProgressDialog.create(new IProgressRunnable() {

        private volatile List<CardDescription> allCards = new LinkedList<CardDescription>();

        @Override
        public void done() {
          dbAccess.saveAllCardDescription(allCards);
          System.out.println("isEDT " + SwingUtilities.isEventDispatchThread());
        }

        @Override
        public void run(final IProgressMonitor monitor) throws Exception {
          monitor.setMessage("Grabbing DB...");
          GrabberJsoup grabberJsoup = new GrabberJsoup("English");
          grabberJsoup.grabAllEditions(new IGrabberListener() {

            int counter = 0;

            @Override
            public void grabbed(final CardDescription description) {
              allCards.add(description);
//              SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                  System.out.println("saving card " + description);
//                  dbAccess.saveAllCardDescription(Arrays.asList(description));
//                }
//              });
              monitor.setMessage("Grabbed card " + description.getName());
              monitor.step(counter++);
            }

          });
        }
      });
      dialog.setMin(0);
      dialog.setMax(249);
      dialog.start();
    }
  }
}
