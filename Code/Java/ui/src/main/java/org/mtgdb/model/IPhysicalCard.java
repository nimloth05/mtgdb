package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public interface IPhysicalCard {

  String getCardId();

  int getContainerId();

  CardCondition getCondition();

  String getLanguage();
}
