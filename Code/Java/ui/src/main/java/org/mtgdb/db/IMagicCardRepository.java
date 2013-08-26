package org.mtgdb.db;

import org.mtgdb.model.CardDescription;

import java.util.Collection;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public interface IMagicCardRepository extends IRepository {

  void saveAll(ITransaction transaction, Collection<CardDescription> cards);

  void deleteAll(ITransaction transaction);

  List<CardDescription> getAllCards();
}
