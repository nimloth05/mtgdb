package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public interface IMagicCard {

  int CREATURE_STAT_STAR_VALUE = -1;

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

  Edition getEdition();

  Rarity getRarity();

  String getName();
}
