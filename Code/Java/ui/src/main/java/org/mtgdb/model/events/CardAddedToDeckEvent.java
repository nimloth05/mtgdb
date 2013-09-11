package org.mtgdb.model.events;

import org.mtgdb.model.MagicCard;

/**
 * @author Sandro Orlando
 */
public final class CardAddedToDeckEvent {

  private final MagicCard magicCard;

  public MagicCard getMagicCard() {
    return magicCard;
  }

  public CardAddedToDeckEvent(final MagicCard magicCard) {
    this.magicCard = magicCard;
  }
}
