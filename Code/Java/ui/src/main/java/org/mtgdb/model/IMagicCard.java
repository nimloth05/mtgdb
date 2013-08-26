package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public interface IMagicCard {

  String getId();

  int getLoyalty();

  String getImageURL();

  int getToughness();

  int getPower();

  int getConvertedManaCost();

  String getManaCost();

  String getSubType();

  String getType();

  String getText();

  String getFlavorText();

  String getNumber();

  String getArtist();

  String getEdition();

  Rarity getRarity();

  String getName();
}
