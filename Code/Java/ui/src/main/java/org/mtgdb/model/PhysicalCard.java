package org.mtgdb.model;

import com.j256.ormlite.field.DatabaseField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Sandro Orlando
 */
@Entity
public final class PhysicalCard implements IPhysicalCard {

  @Id
  @GeneratedValue
  private int id;
  @DatabaseField(columnName = "card_id", foreignAutoRefresh = true, foreign = true)
  private MagicCard card;
  @ManyToOne
  private Container container;
  @Column
  private CardCondition condition;
  @Column
  private String language;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  @Override
  public MagicCard getCard() {
    return card;
  }

  public void setCard(final MagicCard card) {
    this.card = card;
  }

  @Override
  public Container getContainer() {
    return container;
  }

  public void setContainer(final Container container) {
    this.container = container;
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
