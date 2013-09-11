package org.mtgdb.db.repository;

import org.mtgdb.model.Deck;
import org.mtgdb.model.DeckMagicCard;

import java.util.Collection;

/**
 * @author Sandro Orlando
 */
public interface IDeckMagicCardRepository extends IRepository<DeckMagicCard> {

  Collection<DeckMagicCard> getForDeck(Deck deck);

  void delete(int id, String id1);
}
