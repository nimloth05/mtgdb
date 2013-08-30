package org.mtgdb.model;

import org.apache.commons.lang.StringUtils;

/**
 * @author Sandro Orlando
 */
public enum Rarity {
  common,
  uncommon,
  rare,
  mythicRare {
    @Override
    public String toDisplayName() {
      return "Mythic Rare";
    }
  };

  public static Rarity parse(final String name) {
    if (StringUtils.containsIgnoreCase(name, "mythic")) return mythicRare;
    return Rarity.valueOf(name.toLowerCase());
  }

  public String toDisplayName() {
    return StringUtils.capitalize(name());
  }

  @Override
  public String toString() {
    return toDisplayName();
  }
}
