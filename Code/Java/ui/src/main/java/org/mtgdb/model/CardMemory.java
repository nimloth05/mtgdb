package org.mtgdb.model;

import com.j256.ormlite.field.DatabaseField;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Sandro Orlando
 */
public final class CardMemory {

  @Id
  @GeneratedValue
  private int id;

  @DatabaseField(columnName = "magiccard_id", foreign = true, foreignAutoRefresh = true)
  private MagicCard magicCard;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public IMagicCard getMagicCard() {
    return magicCard;
  }

  public void setMagicCard(final MagicCard magicCard) {
    this.magicCard = magicCard;
  }
}
