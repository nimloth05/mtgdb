package org.mtgdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Sandro Orlando
 */
@Entity
public final class Edition {

  @Id
  private String id;

  @Column
  private String name;

  @Column
  private int numberOfCards;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public int getNumberOfCards() {
    return numberOfCards;
  }

  public void setNumberOfCards(final int numberOfCards) {
    this.numberOfCards = numberOfCards;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Edition{" +
      "edition='" + name + '\'' +
      ", editionId='" + id + '\'' +
      ", numberOfCards=" + numberOfCards +
      '}';
  }
}
