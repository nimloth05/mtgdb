package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public final class Edition {

  private String edition;
  private String editionId;
  private int numberOfCards;

  public String getEditionId() {
    return editionId;
  }

  public void setEditionId(final String editionId) {
    this.editionId = editionId;
  }

  public int getNumberOfCards() {
    return numberOfCards;
  }

  public void setNumberOfCards(final int numberOfCards) {
    this.numberOfCards = numberOfCards;
  }

  public String getEdition() {
    return edition;
  }

  public void setEdition(final String edition) {
    this.edition = edition;
  }
}
