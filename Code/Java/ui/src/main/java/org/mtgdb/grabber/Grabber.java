package org.mtgdb.grabber;

import org.mtgdb.model.Edition;
import org.mtgdb.model.MagicCard;

import java.io.IOException;

/**
 * @author Sandro Orlando
 */
public final class Grabber {

  public static final String urlPrefix = "http://magiccards.info/";
  private final String lang;

  public Grabber(final String lang) {
    this.lang = lang;
  }

  public void grabAllEditions(final IGrabberListener listener) throws IOException {
    doGrab(listener);
  }

  private void doGrab(final IGrabberListener listener) {
    EditionGrabber grabber = new EditionGrabber(lang);
    grabber.startGrabbing(new CardListGrabber(new ICardListGrabberListener() {

      private Edition edition;
      private CardGrabber cardGrabber = new CardGrabber();

      @Override
      public void begin(final String name, final String editionId, final int numberOfCards) {
        edition = new Edition();
        edition.setName(name);
        edition.setId(editionId);
        edition.setNumberOfCards(numberOfCards);
        listener.beginEdition(edition);
      }

      @Override
      public void grabCard(final String cardUrl, final String rarity, final String name) {
        final MagicCard magicCard = cardGrabber.grabCard(cardUrl, rarity, name);
        magicCard.setEdition(edition);
        magicCard.setId();
        listener.grabbed(magicCard);
      }

      @Override
      public void end() {
        listener.endEdition();
      }
    }));
  }


}
