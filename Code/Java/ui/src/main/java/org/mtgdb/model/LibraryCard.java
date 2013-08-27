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
  public Container getContainer() {
    return physicalCard.getContainer();
  }

  public void setContainerId(final Container containerId) {
    physicalCard.setContainer(containerId);
  }

  @Override
  public MagicCard getCard() {
    return physicalCard.getCard();
  }

  public void setCardId(final MagicCard cardId) {
    physicalCard.setCard(cardId);
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
  public Edition getEdition() {
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
