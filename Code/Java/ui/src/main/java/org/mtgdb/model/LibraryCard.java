package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public final class LibraryCard implements ILibraryCard {

  private IMagicCard card;
  private PhysicalCard physicalCard;

  public LibraryCard(final IMagicCard card, final PhysicalCard physicalCard) {
    this.card = card;
    this.physicalCard = physicalCard;
  }

  @Override
  public String getLanguage() {
    return physicalCard.getLanguage();
  }

  public void setLanguage(final String language) {
    physicalCard.setLanguage(language);
  }

  @Override
  public CardCondition getCondition() {
    return physicalCard.getCondition();
  }

  public void setCondition(final CardCondition condition) {
    physicalCard.setCondition(condition);
  }

  @Override
  public int getContainerId() {
    return physicalCard.getContainerId();
  }

  public void setContainerId(final int containerId) {
    physicalCard.setContainerId(containerId);
  }

  @Override
  public String getCardId() {
    return physicalCard.getCardId();
  }

  public void setCardId(final String cardId) {
    physicalCard.setCardId(cardId);
  }

  @Override
  public String getId() {
    return card.getId();
  }

  public void setId(final int id) {
    physicalCard.setId(id);
  }

  @Override
  public int getLoyalty() {
    return card.getLoyalty();
  }

  @Override
  public String getImageURL() {
    return card.getImageURL();
  }

  @Override
  public int getToughness() {
    return card.getToughness();
  }

  @Override
  public int getPower() {
    return card.getPower();
  }

  @Override
  public int getConvertedManaCost() {
    return card.getConvertedManaCost();
  }

  @Override
  public String getManaCost() {
    return card.getManaCost();
  }

  @Override
  public String getSubType() {
    return card.getSubType();
  }

  @Override
  public String getType() {
    return card.getType();
  }

  @Override
  public String getText() {
    return card.getText();
  }

  @Override
  public String getFlavorText() {
    return card.getFlavorText();
  }

  @Override
  public String getNumber() {
    return card.getNumber();
  }

  @Override
  public String getArtist() {
    return card.getArtist();
  }

  @Override
  public String getEdition() {
    return card.getEdition();
  }

  @Override
  public Rarity getRarity() {
    return card.getRarity();
  }

  @Override
  public String getName() {
    return card.getName();
  }
}
