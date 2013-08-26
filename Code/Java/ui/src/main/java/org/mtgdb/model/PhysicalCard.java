package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public final class PhysicalCard implements IPhysicalCard {

  private int id;
  private String cardId;
  private int containerId;
  private CardCondition condition;
  private String language;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  @Override
  public String getCardId() {
    return cardId;
  }

  public void setCardId(final String cardId) {
    this.cardId = cardId;
  }

  @Override
  public int getContainerId() {
    return containerId;
  }

  public void setContainerId(final int containerId) {
    this.containerId = containerId;
  }

  @Override
  public CardCondition getCondition() {
    return condition;
  }

  public void setCondition(final CardCondition condition) {
    this.condition = condition;
  }

  @Override
  public String getLanguage() {
    return language;
  }

  public void setLanguage(final String language) {
    this.language = language;
  }
}
