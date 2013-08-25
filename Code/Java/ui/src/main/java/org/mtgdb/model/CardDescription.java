package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public final class CardDescription {
  private String type;
  private String subType;
  private String manaCost;
  private int convManaCost;
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

  public String getCardId() {
    return cardId;
  }

  public void setCardId(final String cardId) {
    this.cardId = cardId;
  }

  private String cardId;

  public int getLoyalty() {
    return loyalty;
  }

  public void setLoyalty(int loyalty) {
    this.loyalty = loyalty;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public int getToughness() {
    return toughness;
  }

  public void setToughness(int hp) {
    this.toughness = hp;
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public int getConvManaCost() {
    return convManaCost;
  }

  public void setConvManaCost(int convManaCost) {
    this.convManaCost = convManaCost;
  }

  public String getManaCost() {
    return manaCost;
  }

  public void setManaCost(String manaCost) {
    this.manaCost = manaCost;
  }

  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCardText() {
    return cardText;
  }

  public void setCardText(String cardText) {
    this.cardText = cardText;
  }

  public String getFlavorText() {
    return flavorText;
  }

  public void setFlavorText(String flavorText) {
    this.flavorText = flavorText;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getEdition() {
    return edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public Rarity getRarity() {
    return rarity;
  }

  public void setRarity(Rarity rarity) {
    this.rarity = rarity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "CardDescription{" +
      "artist='" + artist + '\'' +
      ", type='" + type + '\'' +
      ", subType='" + subType + '\'' +
      ", manaCost='" + manaCost + '\'' +
      ", convManaCost=" + convManaCost +
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
