package org.mtgdb.grabber;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.mtgdb.model.IMagicCard;
import org.mtgdb.model.MagicCard;
import org.mtgdb.model.Rarity;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sandro Orlando
 */
public final class CardGrabber {

  public MagicCard grabCard(final String url, final String rarity, final String name) {
    try {
      return doGrabCard(url, rarity, name);
    } catch (IOException e) {
      return null;
    }
  }

  private MagicCard doGrabCard(final String cardUrl, final String rarity, final String name) throws IOException {
    URL urlObject = new URL(cardUrl);
    final java.io.BufferedReader bufferedReader = new java.io.BufferedReader(new java.io.InputStreamReader(urlObject.openConnection().getInputStream(), "utf8"));
    String line;
    StringBuilder builder = new StringBuilder();
    while ((line = bufferedReader.readLine()) != null) {
      builder.append(line);
    }

    final String html = builder.toString();
    MagicCard card = new MagicCard();
    card.setName(name);

    Document document = Jsoup.parse(html);

    extractTypeLineSandro(card, document);
    extractCardText(card, document);
    extractFlavourText(card, document);
    extractImageURL(card, html);
    extractNrArtist(card, document);
    card.setRarity(Rarity.parse(rarity));
    return card;
  }

  private void extractTypeLineSandro(final MagicCard card, final Document document) {
    Elements typeLineElement = document.select("table > tbody > tr span + p");
    String typeLine = typeLineElement.text();
    String[] lineParts = typeLine.split(",");
    String[] types = lineParts[0].split("—");
    card.setType(StringUtils.trim(types[0]));
    if (types.length > 1) {
      final String subTypes = types[1];

      //Possible creature with power/toughness
      Pattern creaturePattern = Pattern.compile("(.*?)(\\d{1,2})/(\\d{1,2})");
      final Matcher creatureMatcher = creaturePattern.matcher(subTypes);

      Pattern createStarPattern = Pattern.compile("(.*?)(\\*)/(\\*)");
      final Matcher createStarMatcher = createStarPattern.matcher(subTypes);

      final Pattern planesWalkerPattern = Pattern.compile("(.*?)\\(\\D*: (\\d{1,2})\\)");
      Matcher planesWalkerMatcher = planesWalkerPattern.matcher(subTypes);

      if (creatureMatcher.find()) {
        card.setSubType(StringUtils.trim(creatureMatcher.group(1)));
        card.setPower(Integer.parseInt(creatureMatcher.group(2)));
        card.setToughness(Integer.parseInt(creatureMatcher.group(3)));
      } else if (createStarMatcher.find()) {
        card.setSubType(StringUtils.trim(createStarMatcher.group(1)));
        card.setPower(IMagicCard.CREATURE_STAT_STAR_VALUE);
        card.setToughness(IMagicCard.CREATURE_STAT_STAR_VALUE);
      } else if (planesWalkerMatcher.find()) {
        card.setSubType(StringUtils.trim(planesWalkerMatcher.group(1)));
        card.setLoyalty(Integer.parseInt(planesWalkerMatcher.group(2)));
      } else {
        card.setSubType(StringUtils.trim(subTypes.trim()));
      }
    } else {
      card.setSubType(null);
    }

    if (lineParts.length > 1) {
      String manaCost = lineParts[1];
      Pattern pattern = Pattern.compile("(\\d{0,2}[A-Z]{0,20}) \\((\\d{1,2})\\)");
      Matcher matcher = pattern.matcher(manaCost);
      if (matcher.find()) {
        card.setManaCost(matcher.group(1));
        card.setConvertedManaCost(Integer.parseInt(matcher.group(2)));
      } else {
        throw new IllegalArgumentException("could not parse mana cost " + card.getName());
      }
    }
  }

  private void extractCardText(MagicCard card, final Document document) {
    card.setCardText(document.select("p.ctext").first().text());
  }

  private void extractFlavourText(MagicCard card, final Document document) {
    card.setFlavorText(document.select("table > tbody > tr span + p + p + p > i").text());
  }

  private void extractImageURL(MagicCard card, final String html) {
    final Pattern patternScanUrl = Pattern.compile(".*(http://magiccards\\.info/scan.*jpg).*");
    Matcher m = patternScanUrl.matcher(html);
    if (m.matches()) {
      card.setImageURL(m.group(1));
    }
  }

  private void extractNrArtist(MagicCard card, final Document document) {
    final String text = document.select("html body table tbody tr td small b").text();
    final Pattern patternCardnumArtist = Pattern.compile("#(\\d{1,3}[a-z]?)\\s\\((.+?)\\)");
    Matcher m = patternCardnumArtist.matcher(text);
    if (m.find()) {
      card.setNumber(m.group(1));
      card.setArtist(m.group(2));
    }
  }
}
