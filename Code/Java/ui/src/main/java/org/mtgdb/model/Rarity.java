package org.mtgdb.model;

/**
 * @author Sandro Orlando
 */
public enum Rarity {
  common,
  uncommon,
  rare,
  mythicRare;

  public static Rarity parse(final String name) {
    return common;
  }
}
