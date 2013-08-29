package org.mtgdb.grabber;

import junit.framework.Assert;
import org.junit.Test;
import org.mtgdb.model.Rarity;

/**
 * @author Sandro Orlando
 */
public final class RarityParseTest {

  @Test
  public void testParse() {
    assertConstant("Common", Rarity.common);
  }

  @Test
  public void testUncommon() {
    assertConstant("Uncommon", Rarity.uncommon);
  }

  @Test
  public void testRare() {
    assertConstant("Rare", Rarity.rare);
  }

  @Test
  public void testMythicRare() {
    assertConstant("Mythic Rare", Rarity.mythicRare);
  }

  private void assertConstant(final String displayName, final Rarity expected) {
    Assert.assertEquals(expected, Rarity.parse(displayName));
  }

}
