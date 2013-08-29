package org.mtgdb.model;

import org.apache.commons.lang.StringUtils;

/**
 * @author Sandro Orlando
 */
public enum Rarity {
  common,
  uncommon,
  rare,
  mythicRare;

  public static Rarity parse(final String name) {
    if (StringUtils.containsIgnoreCase(name, "mythic")) return mythicRare;
    return Rarity.valueOf(name.toLowerCase());
  }

  public String toDisplayName() {
    return StringUtils.capitalize(name());

  }
}
