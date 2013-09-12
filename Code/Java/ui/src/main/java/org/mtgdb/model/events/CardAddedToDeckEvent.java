package org.mtgdb.model.events;

import org.mtgdb.model.Deck;
import org.mtgdb.model.MagicCard;

/**
 * @author Sandro Orlando
 */
public final class CardAddedToDeckEvent {

  private final MagicCard magicCard;
  private final Deck deck;

  public CardAddedToDeckEvent(final MagicCard magicCard, final Deck deck) {
    this.magicCard = magicCard;
    this.deck = deck;
  }

  public Deck getDeck() {
    return deck;
  }

  public MagicCard getMagicCard() {
    return magicCard;
  }
}
