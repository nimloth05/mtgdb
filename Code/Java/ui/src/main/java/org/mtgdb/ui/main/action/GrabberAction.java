package org.mtgdb.ui.main.action;

import com.google.inject.Inject;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.ITransactionRunnable;
import org.mtgdb.db.repository.IEditionRepository;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.grabber.Grabber;
import org.mtgdb.grabber.IGrabberListener;
import org.mtgdb.model.Edition;
import org.mtgdb.model.MagicCard;
import org.mtgdb.ui.util.frame.progress.IProgressMonitor;
import org.mtgdb.ui.util.frame.progress.IProgressRunnable;
import org.mtgdb.ui.util.frame.progress.ProgressDialog;
import org.mtgdb.util.assertion.Assert;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    final Map<String, Integer> editionToNumberOfCards = new HashMap<>();
    connection.execute(new ITransactionRunnable() {

      @Override
      public void run() throws Exception {
        editionRepository.gatherEditionInformation(editionToNumberOfCards);
      }
    });

    ProgressDialog dialog = ProgressDialog.create(new IProgressRunnable() {

      private volatile List<MagicCard> allCards = new LinkedList<>();

      @Override
      public void done() {
        magicCardRepository.updateLuceneIndex();
      }

      @Override
      public void run(final IProgressMonitor monitor) throws Exception {
        monitor.setMessage("Grabbing DB...");
        Grabber grabber = new Grabber("English");
        grabber.grabAllEditions(new IGrabberListener() {

          int counter = 0;

          @Override
          public boolean beginEdition(final Edition edition) {
            if (Integer.valueOf(edition.getNumberOfCards()).equals(editionToNumberOfCards.get(edition.getId()))) {
              return true;
            }
            updateEdition(edition);
            return false;
          }

          private void updateEdition(final Edition edition) {
            SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
                Assert.log("Edition grabbed: " + edition);
                connection.execute(new ITransactionRunnable() {
                  @Override
                  public void run() throws Exception {
                    //We delete the edition here to guarantee an update to view of the data
                    editionRepository.delete(edition);
                    magicCardRepository.deleteCardsForEdition(edition);

                    editionRepository.save(edition);
                  }
                });
              }
            });
          }

          @Override
          public void grabbed(final MagicCard description) {
            allCards.add(description);
            monitor.setMessage("Grabbed card " + description.getName());
            monitor.step(counter++);
          }

          @Override
          public void endEdition() {
            final List<MagicCard> cardsToSave = allCards;
            allCards = new LinkedList<>();

            SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
                connection.execute(new ITransactionRunnable() {
                  @Override
                  public void run() throws Exception {
                    magicCardRepository.saveAll(cardsToSave);
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
