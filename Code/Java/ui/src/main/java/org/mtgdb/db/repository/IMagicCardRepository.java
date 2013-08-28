package org.mtgdb.db.repository;

import org.mtgdb.model.Edition;
import org.mtgdb.model.MagicCard;

import java.util.Collection;

/**
 * @author Sandro Orlando
 */
public interface IMagicCardRepository extends IRepository<MagicCard> {

  void saveAll(Collection<MagicCard> cards);

  void deleteAll();

  MagicCard getCard(Edition edition, String cardNumber);
}
