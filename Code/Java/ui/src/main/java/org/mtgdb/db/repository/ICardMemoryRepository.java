package org.mtgdb.db.repository;

import org.mtgdb.model.CardMemory;
import org.mtgdb.model.IMagicCard;

/**
 * @author Sandro Orlando
 */
public interface ICardMemoryRepository extends IRepository<CardMemory> {

  void rememberCard(IMagicCard currentCard);

}
