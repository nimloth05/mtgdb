package org.mtgdb.model;

/**
 * @author Michael Sacher
 */
public class MagicCard {
  public final String cardNumber;
  public final String type;
  public final String subtype;
  public final String manaCost;
  public final int convManaCost;
  public final int power;
  public final int toughness;
  public final String imgUrl;
  public final String cardText;
  public final String flavorText;
  public final String artist;
  public final int rarity;
  public final String name;
  public final String cardId;
  public String refEdition;

  public MagicCard(String refEdition,
                   String cardNumber,
                   String type,
                   String subtype,
                   String manaCost,
                   int convManaCost,
                   int power,
                   int toughness,
                   String imgUrl,
                   String cardText,
                   String flavorText,
                   String artist,
                   int rarity,
                   String name,
                   String cardId
  ) {
    this.refEdition = refEdition;
    this.cardNumber = cardNumber;
    this.type = type;
    this.subtype = subtype;
    this.manaCost = manaCost;
    this.convManaCost = convManaCost;
    this.power = power;
    this.toughness = toughness;
    this.imgUrl = imgUrl;
    this.cardText = cardText;
    this.flavorText = flavorText;
    this.artist = artist;
    this.rarity = rarity;
    this.name = name;
    this.cardId = cardId;
  }

  public Object[] toArray() {
    return new Object[]{refEdition,
      cardNumber,
      type,
      subtype,
      manaCost,
      convManaCost,
      power,
      toughness,
      imgUrl,
      cardText,
      flavorText,
      artist,
      rarity,
      name,
      cardId};
  }
}
