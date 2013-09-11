package org.mtgdb.model.events;

import org.mtgdb.model.MagicCard;

/**
 * @author Sandro Orlando
 */
public final class CardRemovedFroMDeck {

  private final MagicCard card;

  public CardRemovedFroMDeck(final MagicCard card) {
    this.card = card;
  }

  public MagicCard getCard() {
    return card;
  }
}
