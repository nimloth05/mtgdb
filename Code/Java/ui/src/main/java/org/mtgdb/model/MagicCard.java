package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public final class MagicCard implements IMagicCard {

  private String type;
  private String subType;
  private String manaCost;
  private int convertedManaCost;
  private int power;
  private int toughness;
  private String imageURL;
  private String cardText;
  private String flavorText;
  private String number;
  private String artist;
  private String edition;
  private Rarity rarity;
  private String name;
  private int loyalty;

  @Override
  public String getId() {
    return cardId;
  }

  public void setCardId(final String cardId) {
    this.cardId = cardId;
  }

  private String cardId;

  @Override
  public int getLoyalty() {
    return loyalty;
  }

  public void setLoyalty(int loyalty) {
    this.loyalty = loyalty;
  }

  @Override
  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  @Override
  public int getToughness() {
    return toughness;
  }

  public void setToughness(int hp) {
    this.toughness = hp;
  }

  @Override
  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  @Override
  public int getConvertedManaCost() {
    return convertedManaCost;
  }

  public void setConvertedManaCost(int convertedManaCost) {
    this.convertedManaCost = convertedManaCost;
  }

  @Override
  public String getManaCost() {
    return manaCost;
  }

  public void setManaCost(String manaCost) {
    this.manaCost = manaCost;
  }

  @Override
  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  @Override
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String getText() {
    return cardText;
  }

  public void setCardText(String cardText) {
    this.cardText = cardText;
  }

  @Override
  public String getFlavorText() {
    return flavorText;
  }

  public void setFlavorText(String flavorText) {
    this.flavorText = flavorText;
  }

  @Override
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  @Override
  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  @Override
  public String getEdition() {
    return edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  @Override
  public Rarity getRarity() {
    return rarity;
  }

  public void setRarity(Rarity rarity) {
    this.rarity = rarity;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "MagicCard{" +
      "artist='" + artist + '\'' +
      ", type='" + type + '\'' +
      ", subType='" + subType + '\'' +
      ", manaCost='" + manaCost + '\'' +
      ", convManaCost=" + convertedManaCost +
      ", power=" + power +
      ", hp=" + toughness +
      ", imageURL='" + imageURL + '\'' +
      ", cardText='" + cardText + '\'' +
      ", flavorText='" + flavorText + '\'' +
      ", number='" + number + '\'' +
      ", edition='" + edition + '\'' +
      ", rarity='" + rarity + '\'' +
      ", name='" + name + '\'' +
      '}';
  }
}
