package org.mtgdb.model;

import org.apache.commons.lang.StringUtils;
import org.mtgdb.util.Constants;

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
  },
  land,
  special;

  public static Rarity parse(String name) {
    int index = name.indexOf(Constants.SPACE);
    if (index > 0) name = name.substring(0, index);
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
