package org.mtgdb.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mtgdb.model.CardDescription;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sandro Orlando
 */
public final class GrabberJsoup {

  private final String urlPrefix = "http://magiccards.info/";
  private final String lang;

  public GrabberJsoup(final String lang) {
    this.lang = lang;
  }

  public void grabAllEditions(final IGrabberListener monitor) throws IOException {
    final String sitemap = "http://magiccards.info/sitemap.html";
    Document doc = Jsoup.connect(sitemap).get();
    Elements table = doc.select(":containsOwn(" + lang + ") + table");
    Elements lists = table.select("tr > td > ul");
    for (Element list : lists) {
      Elements items = list.getAllElements();
      Elements editions = items.select("a");
      editions.addAll(items.select("small"));

      for (Element edition : editions) {
        System.out.println(edition.tagName());
        if (edition.tagName().equals("a")) {
          final String edStr = edition.nextElementSibling().text();
          final String edUrl = edition.attr("href");
          grabEdition(urlPrefix + edUrl, edStr, monitor);
          System.out.println("Edition grabbed");

          break;
        }
      }
      break;
    }
  }

  public void grabEdition(final String editionUrl, final String editionShort, final IGrabberListener monitor) throws IOException {
    Document doc = Jsoup.connect(editionUrl).get();
    Elements tables = doc.select("table .even");
    tables.addAll(doc.select("table .odd"));

    for (Element row : tables) {
      final Elements td = row.select("td");
      final String text = td.get(1).text();
      CardDescription cardDescription = grabCard("" + urlPrefix + td.get(1).child(0).attr("href"), editionShort, td.get(4).text(), text);
      monitor.grabbed(cardDescription);
    }
  }

  private CardDescription grabCard(final String cardUrl, final String edition, final String rarity, final String name) throws IOException {
    URL urlObject = new URL(cardUrl);
    final java.io.BufferedReader bufferedReader = new java.io.BufferedReader(new java.io.InputStreamReader(urlObject.openConnection().getInputStream(), "utf8"));
    String line;
    StringBuilder builder = new StringBuilder();
    while ((line = bufferedReader.readLine()) != null) {
      builder.append(line);
    }

    final String html = builder.toString();
    CardDescription card = new CardDescription();
    Document document = Jsoup.parse(html);


    extractTypeLine(card, document);
    extractTypeLineOther(card, document);
    extractTypeLineCreature(card, document);
    extractTypeLinePlaneswalker(card, document);
    extractCardText(card, document);
    extractFlavourText(card, document);
    extractImageURL(card, html);
    extractNrArtist(card, document);
    card.setEdition(edition);
    card.setRarity(rarity);
    card.setName(name);

    return card;
  }

  private void extractTypeLine(CardDescription card, final Document document) {
    Elements typeline = document.select("table > tbody > tr span + p");
    String type = typeline.text();
    final Pattern patternTypeline = Pattern.compile("(.*),\\s\\s*([0-9A-Z]+)\\s\\(([0-9]+)\\)");
    Matcher m = patternTypeline.matcher(type);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setManaCost(m.group(2));
      card.setConvManaCost(Integer.parseInt(m.group(3)));
    }
  }

  private void extractTypeLineOther(CardDescription card, final Document document) {
    Elements typeline = document.select("table > tbody > tr span + p");
    String type = typeline.text();
    final Pattern patternTypelineOther = Pattern.compile("(.*)\\s—\\s(.*)");
    Matcher m = patternTypelineOther.matcher(type);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setSubType(m.group(2));
    }
  }

  private void extractTypeLineCreature(CardDescription card, final Document document) {
    Elements typeline = document.select("table > tbody > tr span + p");
    String type = typeline.text();
    final Pattern patternTypelineCreature = Pattern.compile("(.*)\\s—\\s(.*)\\s(\\d{1,2}|\\*)/(\\d{1,2}|\\*),\\s*([0-9A-Z]+)\\s\\(([0-9]+)\\)");
    Matcher m = patternTypelineCreature.matcher(type);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setSubType(m.group(2));
      card.setPower(Integer.parseInt(m.group(3)));
      card.setHp(Integer.parseInt(m.group(4)));
      card.setManaCost(m.group(5));
      card.setConvManaCost(Integer.parseInt(m.group(6)));
    }
  }
  private void extractTypeLinePlaneswalker(CardDescription card, final Document document) {
    Elements typeline = document.select("table > tbody > tr span + p");
    String type = typeline.text();
    final Pattern patternTypelinePlaneswalker = Pattern.compile("(.*)\\s—\\s(.*)\\s\\([a-zA-z]+:\\s(\\d{1,2})\\),\\s([0-9A-Z]+)\\s\\(([0-9]+)\\)");
    Matcher m = patternTypelinePlaneswalker.matcher(type);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setSubType(m.group(2));
      card.setLoyalty(Integer.parseInt(m.group(3)));
      card.setManaCost(m.group(4));
      card.setConvManaCost(Integer.parseInt(m.group(5)));
    }
  }

  private void extractCardText(CardDescription card, final Document document) {
    card.setCardText(document.select("p.ctext").first().text());
  }

  private void extractFlavourText(CardDescription card, final Document document) {
    card.setFlavorText(document.select("table > tbody > tr span + p + p + p > i").text());
  }

  private void extractImageURL(CardDescription card, final String html) {
    final Pattern patternScanUrl = Pattern.compile("\"(http://magiccards\\.info/scan.*)\"");
    Matcher m = patternScanUrl.matcher(html);
    if (m.matches()) {
      card.setImageURL(m.group(1));
    }
  }

  private void extractNrArtist(CardDescription card, final Document document) {
    final String text = document.select("html body table tbody tr td small b").text();
    final Pattern patternCardnumArtist = Pattern.compile("#(\\d{1,3}[a-z]?)\\s\\((.+?)\\)");
    Matcher m = patternCardnumArtist.matcher(text);
    if (m.find()) {
      card.setNumber(m.group(1));
      card.setArtist(m.group(2));
    }
  }
}
