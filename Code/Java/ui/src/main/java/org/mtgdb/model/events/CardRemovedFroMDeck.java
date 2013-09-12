package org.mtgdb.model.events;

import org.mtgdb.model.Deck;
import org.mtgdb.model.MagicCard;

/**
 * @author Sandro Orlando
 */
public final class CardRemovedFroMDeck {

  private final MagicCard card;
  private final Deck deck;

  public CardRemovedFroMDeck(final MagicCard card, final Deck deck) {
    this.card = card;
    this.deck = deck;

  }

  public Deck getDeck() {
    return deck;
  }

  public MagicCard getCard() {
    return card;
  }
}
