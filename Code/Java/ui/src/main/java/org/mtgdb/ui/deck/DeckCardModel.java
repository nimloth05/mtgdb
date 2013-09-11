package org.mtgdb.ui.deck;

import org.mtgdb.model.Deck;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.card.MagicCardPanelModel;

/**
 * @author Sandro Orlando
 */
public final class DeckCardModel {

  private MagicCardPanelModel deckCards;
  private MagicCardPanelModel cardDatabase;

  public DeckCardModel(final Deck deck) {
    deckCards = ServiceManager.get(MagicCardPanelModel.class);
    updateDeckCards();

    cardDatabase = ServiceManager.get(MagicCardPanelModel.class);
    cardDatabase.showAll();
  }

  private void updateDeckCards() {

  }


}
