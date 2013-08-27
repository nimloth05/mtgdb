package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public interface IPhysicalCard {

  MagicCard getCard();

  Container getContainer();

  CardCondition getCondition();

  String getLanguage();
}
