package org.mtgdb.db.repository;

import org.mtgdb.db.ITransaction;
import org.mtgdb.model.IMagicCard;

import java.util.Collection;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public interface IMagicCardRepository extends IRepository {

  void saveAll(ITransaction transaction, Collection<IMagicCard> cards);

  void deleteAll(ITransaction transaction);

  List<IMagicCard> getAllCards();
}
