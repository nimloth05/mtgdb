package org.mtgdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Sandro Orlando
 */
public final class CardMemory {

  @Id
  @GeneratedValue
  private int id;

  @Column
  private IMagicCard magicCard;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public IMagicCard getMagicCard() {
    return magicCard;
  }

  public void setMagicCard(final IMagicCard magicCard) {
    this.magicCard = magicCard;
  }
}
