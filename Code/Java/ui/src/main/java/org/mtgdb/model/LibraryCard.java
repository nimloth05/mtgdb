package org.mtgdb.model;

import org.mtgdb.util.Constants;

/**
 * @author Sandro Orlando
 */
public final class LibraryCard implements ILibraryCard {

  private String name;
  private String container;

  public LibraryCard(final String name, final String container) {
    this.name = name;
    this.container = container;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getCost() {
    return Constants.EMPTY;
  }

  @Override
  public String getContainer() {
    return container;
  }
}
