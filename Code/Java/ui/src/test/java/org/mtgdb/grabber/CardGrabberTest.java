package org.mtgdb.grabber;

import org.junit.Assert;
import org.junit.Test;
import org.mtgdb.model.IMagicCard;
import org.mtgdb.model.MagicCard;
import org.mtgdb.model.Rarity;
import org.mtgdb.util.Constants;

/**
 * @author Sandro Orlando
 */
public final class CardGrabberTest {

  @Test
  public void testLandCard() {
    MagicCard expectedCard = new MagicCard();
    expectedCard.setArtist("Howard Lyon");
    expectedCard.setConvertedManaCost(0);
    expectedCard.setFlavorText("The Selesyna welcome all to help them heal the city's wounds. The price, however, is devotion to the guild and a selfless belief in Trostani.");
    expectedCard.setImageURL("http://magiccards.info/scans/en/dgm/155.jpg");
    expectedCard.setLoyalty(0);
    expectedCard.setManaCost(null);
    expectedCard.setName("Selesnya Guildgate");
    expectedCard.setPower(0);
    expectedCard.setRarity(Rarity.common);
    expectedCard.setSubType("Gate");
    expectedCard.setCardText("Selesnya Guildgate enters the battlefield tapped. {T}: Add {G} or {W} to your mana pool.");
    expectedCard.setToughness(0);
    expectedCard.setType("Land");
    expectedCard.setNumber("155");
    assertCard(expectedCard, "http://magiccards.info//dgm/en/155.html", "Common", "Selesnya Guildgate");
  }

  @Test
  public void testNormalCreature() {
    MagicCard expectedCard = new MagicCard();
    expectedCard.setArtist("Kev Walker");
    expectedCard.setConvertedManaCost(2);
    expectedCard.setFlavorText(Constants.EMPTY);
    expectedCard.setImageURL("http://magiccards.info/scans/en/dgm/1.jpg");
    expectedCard.setLoyalty(0);
    expectedCard.setManaCost("1W");
    expectedCard.setName("Boros Mastiff");
    expectedCard.setPower(2);
    expectedCard.setRarity(Rarity.common);
    expectedCard.setSubType("Hound");
    expectedCard.setCardText("Battalion — Whenever Boros Mastiff and at least two other creatures attack, Boros Mastiff gains lifelink until end of turn. (Damage dealt by a creature with lifelink also causes its controller to gain that much life.)");
    expectedCard.setToughness(2);
    expectedCard.setType("Creature");
    expectedCard.setNumber("1");
    assertCard(expectedCard, "http://magiccards.info/dgm/en/1.html", "Common", expectedCard.getName());
  }

  @Test
  public void testPlanesWalker() {
    MagicCard expectedCard = new MagicCard();
    expectedCard.setArtist("D. Alexander Gregory");
    expectedCard.setConvertedManaCost(3);
    expectedCard.setFlavorText(Constants.EMPTY);
    expectedCard.setImageURL("http://magiccards.info/scans/en/m14/1.jpg");
    expectedCard.setLoyalty(4);
    expectedCard.setManaCost("1WW");
    expectedCard.setName("Ajani, Caller of the Pride");
    expectedCard.setPower(0);
    expectedCard.setRarity(Rarity.mythicRare);
    expectedCard.setSubType("Ajani");
    expectedCard.setCardText("+1: Put a +1/+1 counter on up to one target creature. -3: Target creature gains flying and double strike until end of turn. -8: Put X 2/2 white Cat creature tokens onto the battlefield, where X is your life total.");
    expectedCard.setToughness(0);
    expectedCard.setType("Planeswalker");
    expectedCard.setNumber("1");
    assertCard(expectedCard, "http://magiccards.info/m14/en/1.html", expectedCard.getRarity().toDisplayName(), expectedCard.getName());
  }

  @Test
  public void testSorcery() {
    MagicCard expectedCard = new MagicCard();
    expectedCard.setArtist("Kev Walker");
    expectedCard.setConvertedManaCost(2);
    expectedCard.setFlavorText("The Azorius have so many codes and statutes that you're always in violation of one of them.");
    expectedCard.setImageURL("http://magiccards.info/scans/en/dgm/3.jpg");
    expectedCard.setLoyalty(0);
    expectedCard.setManaCost("1W");
    expectedCard.setName("Lyev Decree");
    expectedCard.setPower(0);
    expectedCard.setRarity(Rarity.common);
    expectedCard.setSubType(null);
    expectedCard.setCardText("Detain up to two target creatures your opponents control. (Until your next turn, those creatures can't attack or block and their activated abilities can't be activated.)");
    expectedCard.setToughness(0);
    expectedCard.setType("Sorcery");
    expectedCard.setNumber("3");
    assertCard(expectedCard, "http://magiccards.info/dgm/en/3.html", "Common", expectedCard.getName());
  }

  @Test
  public void testInstant() {
    MagicCard expectedCard = new MagicCard();
    expectedCard.setArtist("Daarken");
    expectedCard.setConvertedManaCost(2);
    expectedCard.setFlavorText("\"Scores will die in the name of peace. That is what you call compromise?\" —Gideon Jura, to Aurelia");
    expectedCard.setImageURL("http://magiccards.info/scans/en/dgm/5.jpg");
    expectedCard.setLoyalty(0);
    expectedCard.setManaCost("1W");
    expectedCard.setName("Renounce the Guilds");
    expectedCard.setPower(0);
    expectedCard.setRarity(Rarity.rare);
    expectedCard.setSubType(null);
    expectedCard.setCardText("Each player sacrifices a multicolored permanent.");
    expectedCard.setToughness(0);
    expectedCard.setType("Instant");
    expectedCard.setNumber("5");
    assertCard(expectedCard, "http://magiccards.info/dgm/en/5.html", "Rare", expectedCard.getName());
  }

  @Test
  public void testEnchantAura() {
    MagicCard expectedCard = new MagicCard();
    expectedCard.setArtist("Karl Kopinski");
    expectedCard.setConvertedManaCost(2);
    expectedCard.setFlavorText(Constants.EMPTY);
    expectedCard.setImageURL("http://magiccards.info/scans/en/dgm/17.jpg");
    expectedCard.setLoyalty(0);
    expectedCard.setManaCost("1U");
    expectedCard.setName("Runner's Bane");
    expectedCard.setPower(0);
    expectedCard.setRarity(Rarity.common);
    expectedCard.setSubType("Aura");
    expectedCard.setCardText("Enchant creature with power 3 or less When Runner's Bane enters the battlefield, tap enchanted creature. Enchanted creature doesn't untap during its controller's untap step.");
    expectedCard.setToughness(0);
    expectedCard.setType("Enchantment");
    expectedCard.setNumber("17");
    assertCard(expectedCard, "http://magiccards.info/dgm/en/17.html", expectedCard.getRarity().toDisplayName(), expectedCard.getName());
  }

  @Test
  public void testCreatureWithStarStats() {
    MagicCard expectedCard = new MagicCard();
    expectedCard.setArtist("Vance Kovacs");
    expectedCard.setConvertedManaCost(6);
    expectedCard.setFlavorText("The thunder of its hooves beats dreams into despair.");
    expectedCard.setImageURL("http://magiccards.info/scans/en/m14/108.jpg");
    expectedCard.setLoyalty(0);
    expectedCard.setManaCost("5B");
    expectedCard.setName("Nightmare");
    expectedCard.setPower(IMagicCard.CREATURE_STAT_STAR_VALUE);
    expectedCard.setRarity(Rarity.common);
    expectedCard.setSubType("Nightmare Horse");
    expectedCard.setCardText("Flying Nightmare's power and toughness are each equal to the number of Swamps you control.");
    expectedCard.setToughness(IMagicCard.CREATURE_STAT_STAR_VALUE);
    expectedCard.setType("Creature");
    expectedCard.setNumber("108");
    assertCard(expectedCard, "http://magiccards.info/m14/en/108.html", expectedCard.getRarity().toDisplayName(), expectedCard.getName());
  }

  private void assertCard(final MagicCard expectedCard, final String url, final String rariry, final String name) {
    CardGrabber grabber = new CardGrabber();
    final MagicCard card = grabber.grabCard(url, rariry, name);
    Assert.assertEquals(expectedCard.getArtist(), card.getArtist());
    Assert.assertEquals(expectedCard.getConvertedManaCost(), card.getConvertedManaCost());
    Assert.assertEquals(expectedCard.getFlavorText(), card.getFlavorText());
    Assert.assertEquals(expectedCard.getImageURL(), card.getImageURL());
    Assert.assertEquals(expectedCard.getLoyalty(), card.getLoyalty());
    Assert.assertEquals(expectedCard.getManaCost(), card.getManaCost());
    Assert.assertEquals(expectedCard.getName(), card.getName());
    Assert.assertEquals(expectedCard.getPower(), card.getPower());
    Assert.assertEquals(expectedCard.getRarity(), card.getRarity());
    Assert.assertEquals(expectedCard.getSubType(), card.getSubType());
    Assert.assertEquals(expectedCard.getText(), card.getText());
    Assert.assertEquals(expectedCard.getToughness(), card.getToughness());
    Assert.assertEquals(expectedCard.getType(), card.getType());
    Assert.assertEquals(expectedCard.getNumber(), card.getNumber());
  }

}
