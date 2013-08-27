package org.mtgdb.ui.main.action;

import com.google.inject.Inject;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.ITransactionRunnable;
import org.mtgdb.db.ITransactionToken;
import org.mtgdb.db.repository.IEditionRepository;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.grabber.GrabberJsoup;
import org.mtgdb.grabber.IGrabberListener;
import org.mtgdb.model.Edition;
import org.mtgdb.model.MagicCard;
import org.mtgdb.ui.util.frame.progress.IProgressMonitor;
import org.mtgdb.ui.util.frame.progress.IProgressRunnable;
import org.mtgdb.ui.util.frame.progress.ProgressDialog;
import org.mtgdb.util.assertion.Assert;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public class GrabberAction extends AbstractAction {

  private final IDatabaseConnection connection;
  private final IMagicCardRepository magicCardRepository;
  private final IEditionRepository editionRepository;

  @Inject
  public GrabberAction(final IDatabaseConnection connection, final IMagicCardRepository magicCardRepository, final IEditionRepository editionRepository) {
    super();
    this.connection = connection;
    this.magicCardRepository = magicCardRepository;
    this.editionRepository = editionRepository;
    putValue(Action.NAME, "Grabb DB");
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    connection.execute(new ITransactionRunnable() {

      @Override
      public void run(final ITransactionToken transaction) throws Exception {
        magicCardRepository.deleteAll(transaction);
        editionRepository.deleteAll(transaction);
      }
    });

    ProgressDialog dialog = ProgressDialog.create(new IProgressRunnable() {

      private volatile List<MagicCard> allCards = new LinkedList<>();

      @Override
      public void done() {
      }

      @Override
      public void run(final IProgressMonitor monitor) throws Exception {
        monitor.setMessage("Grabbing DB...");
        GrabberJsoup grabberJsoup = new GrabberJsoup("English");
        grabberJsoup.grabAllEditions(new IGrabberListener() {

          int counter = 0;

          @Override
          public void beginEdition(final Edition edition) {
            saveEdition(edition);
          }

          private void saveEdition(final Edition edition) {
            SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
                Assert.log("Edition grabbed: " + edition);
                connection.execute(new ITransactionRunnable() {
                  @Override
                  public void run(final ITransactionToken transaction) throws Exception {
                    editionRepository.save(transaction, edition);
                  }
                });
              }
            });
          }

          @Override
          public void grabbed(final MagicCard description) {
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

          @Override
          public void endEdition() {
            SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
                connection.execute(new ITransactionRunnable() {
                  @Override
                  public void run(final ITransactionToken transaction) throws Exception {
                    magicCardRepository.saveAll(transaction, allCards);
                  }
                });
              }
            });
          }

        });
      }
    });
    dialog.setMin(0);
    dialog.setMax(249);
    dialog.start();
  }
}
