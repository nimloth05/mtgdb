package org.mtgdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Sandro Orlando
 */
@Entity
public final class Container {

  @Id
  @GeneratedValue
  private int id;

  @Column(nullable = false)
  private String name;

  @Column
  private String description;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Container{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      '}';
  }

  public void setId(final int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
