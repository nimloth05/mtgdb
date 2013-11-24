package org.mtgdb.db.repository;

import org.mtgdb.model.Edition;
import org.mtgdb.model.MagicCard;

import java.util.Collection;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public interface IMagicCardRepository extends IRepository<MagicCard> {

  void saveAll(Collection<MagicCard> cards);

  MagicCard getCard(Edition edition, String cardNumber);

  List<MagicCard> searchFreeText(String text);

  public void updateLuceneIndex();

  /**
   * Deletes all cards from a specific edition
   * @param edition Edition of the cards to delete
   */
  void deleteCardsForEdition(Edition edition);

}
