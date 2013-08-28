package org.mtgdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Sandro Orlando
 */
public final class Deck {

  @Id
  @GeneratedValue
  private int id;
  @Column
  private String name;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }
}
