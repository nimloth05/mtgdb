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
  private int hp;
  private String imageURL;
  private String cardText;
  private String flavorText;
  private String number;
  private String artist;
  private String edition;
  private String rarity;
  private String name;

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
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

  public void setNumber(String number) {
    this.number = number;
  }

  public String getNumber() {
    return number;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getArtist() {
    return artist;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public String getEdition() {
    return edition;
  }

  public void setRarity(String rarity) {
    this.rarity = rarity;
  }

  public String getRarity() {
    return rarity;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
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
      ", hp=" + hp +
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
