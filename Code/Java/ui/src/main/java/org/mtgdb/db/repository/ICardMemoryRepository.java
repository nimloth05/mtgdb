package org.mtgdb.db.repository;

import org.mtgdb.model.CardMemory;
import org.mtgdb.model.MagicCard;

/**
 * @author Sandro Orlando
 */
public interface ICardMemoryRepository extends IRepository<CardMemory> {

  void rememberCard(MagicCard currentCard);

}
