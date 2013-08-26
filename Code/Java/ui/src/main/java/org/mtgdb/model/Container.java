package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public final class Container {

  private int id;
  private String name;
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
