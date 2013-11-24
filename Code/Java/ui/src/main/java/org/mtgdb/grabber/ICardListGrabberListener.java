package org.mtgdb.grabber;

/**
 * @author Sandro Orlando
 */
public interface ICardListGrabberListener {

  boolean setup(String name, String editionId, int numberOfCards);

  void grabCard(final String cardUrl, final String rarity, final String name);

  void complete();
}
