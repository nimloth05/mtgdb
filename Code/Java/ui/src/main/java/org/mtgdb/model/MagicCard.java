package org.mtgdb.model;

import com.j256.ormlite.field.DatabaseField;
import org.mtgdb.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Sandro Orlando
 */
@Entity
public final class MagicCard implements IMagicCard {

  @Id
  private String cardId;
  @Column
  private String type;
  @Column
  private String subType;
  @Column
  private String manaCost;
  @Column
  private int convertedManaCost;
  @Column
  private int power;
  @Column
  private int toughness;
  @Column
  private String imageURL;
  @Column(length =  5000)
  private String cardText;
  @Column(length =  5000)
  private String flavorText;
  @Column
  private String number;
  @Column
  private String artist;
  @DatabaseField(columnName = "edition_id", foreign = true, foreignAutoRefresh = true)
  private Edition edition;
  @Column
  private Rarity rarity;
  @Column
  private String name;
  @Column
  private int loyalty;

  public static String constructId(final Edition edition, final String cardNumber) {
    return edition.getId() + Constants.UNDERSCORE + cardNumber;
  }

  @Override
  public String getId() {
    return cardId;
  }

  public void setCardId(final String cardId) {
    this.cardId = cardId;
  }

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
  public Edition getEdition() {
    return edition;
  }

  public void setEdition(Edition edition) {
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

  public void setId() {
    cardId = constructId(edition, number);
  }
}
