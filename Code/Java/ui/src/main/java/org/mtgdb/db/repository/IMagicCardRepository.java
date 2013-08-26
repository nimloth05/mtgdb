package org.mtgdb.db.repository;

import org.mtgdb.db.ITransaction;
import org.mtgdb.model.MagicCard;

import java.util.Collection;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public interface IMagicCardRepository extends IRepository {

  void saveAll(ITransaction transaction, Collection<MagicCard> cards);

  void deleteAll(ITransaction transaction);

  List<MagicCard> getAllCards();
}
