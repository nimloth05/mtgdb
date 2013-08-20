package org.mtgdb.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mtgdb.model.CardDescription;

import java.io.IOException;

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

    int counter = -1;
    for (Element row : tables) {
      final Elements td = row.select("td");
      // System.out.println("Card: " + td.get(0).text() + " " + td.get(1).text() + " url: " + td.get(1).child(0).attr("href"));
      //System.out.println("\n\nurl: "+urlPrefix+td.get(1).child(0).attr("href")+"\nEdition: "+doc.title()+"\nRarity: "+td.get(4).text()+ "\nName: " +td.get(1).text());
      final String title = doc.title();
      final String text = td.get(1).text();
      CardDescription cardDescription = grabCard("" + urlPrefix + td.get(1).child(0).attr("href"), editionShort, td.get(4).text(), text);
      monitor.grabbed(cardDescription);
    }
//    System.out.println(tables);
  }

  private CardDescription grabCard(final String cardUrl, final String edition, final String rarity, final String name) throws IOException {

    // final java.util.regex.Pattern patternEditionRarity = java.util.regex.Pattern.compile("");

    java.net.URL urlObject = new java.net.URL(cardUrl);
    final java.io.BufferedReader bufferedReader = new java.io.BufferedReader(new java.io.InputStreamReader(urlObject.openConnection().getInputStream(), "utf8"));
    String line;
    StringBuilder builder = new StringBuilder();
    while ((line = bufferedReader.readLine()) != null) {
      builder.append(line);
    }

    final String html = builder.toString();
    org.mtgdb.model.CardDescription card = new org.mtgdb.model.CardDescription();
    Document document = Jsoup.parse(html);


    extractTypeLine(card, document);
    extractTypeLineOther(card, document);
    extractTypeLineCreature(card, document);
    extractCardText(card, document);
    extractFlavourText(card, html);
    extractImageURL(card, html);
    extractNrArtist(card, html);
    card.setEdition(edition);
    card.setRarity(rarity);
    card.setName(name);

    //System.out.println("=========================================================");
    //System.out.println(card.toString());
    return card;
  }

  private void extractTypeLine(org.mtgdb.model.CardDescription card, final Document document) {
    Elements typeline = document.select("table > tbody > tr span + p");
    String type = typeline.text();
    final java.util.regex.Pattern patternTypeline = java.util.regex.Pattern.compile("(.*),\\s\\s*([0-9A-Z]+)\\s\\(([0-9]+)\\)");
    java.util.regex.Matcher m = patternTypeline.matcher(type);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setManaCost(m.group(2));
      card.setConvManaCost(Integer.parseInt(m.group(3)));
    }
  }

  private void extractTypeLineOther(org.mtgdb.model.CardDescription card, final Document document) {
    Elements typeline = document.select("table > tbody > tr span + p");
    String type = typeline.text();
    final java.util.regex.Pattern patternTypelineOther = java.util.regex.Pattern.compile("(.*)\\s—\\s(.*)");
    java.util.regex.Matcher m = patternTypelineOther.matcher(type);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setSubType(m.group(2));
    }
  }

  private void extractTypeLineCreature(org.mtgdb.model.CardDescription card, final Document document) {
    Elements typeline = document.select("table > tbody > tr span + p");
    String type = typeline.text();
    final java.util.regex.Pattern patternTypelineCreature = java.util.regex.Pattern.compile("(.*)\\s—\\s(.*)\\s(\\d{1,2}|\\*)/(\\d{1,2}|\\*),\\s*([0-9A-Z]+)\\s\\(([0-9]+)\\)");
    java.util.regex.Matcher m = patternTypelineCreature.matcher(type);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setSubType(m.group(2));
      card.setPower(Integer.parseInt(m.group(3)));
      card.setHp(Integer.parseInt(m.group(4)));
      card.setManaCost(m.group(5));
      card.setConvManaCost(Integer.parseInt(m.group(6)));
    }
  }

  private void extractCardText(org.mtgdb.model.CardDescription card, final Document document) {
//    final java.util.regex.Pattern patternCardText = java.util.regex.Pattern.compile("<p class=\"ctext\"><b>(.+?)</b></p>");
//    java.util.regex.Matcher m = patternCardText.matcher(html);
//    if (m.find()) {
//      card.setCardText(m.group(1));
//    }
    card.setCardText(document.select("p.ctext").first().text());
  }

  private void extractFlavourText(org.mtgdb.model.CardDescription card, final String html) {
    final java.util.regex.Pattern patternFlavorText = java.util.regex.Pattern.compile("<p><i>(.*)</i></p>");
    java.util.regex.Matcher m = patternFlavorText.matcher(html);
    if (m.find()) {
      card.setFlavorText(m.group(1));
    }
  }

  private void extractImageURL(org.mtgdb.model.CardDescription card, final String html) {
    final java.util.regex.Pattern patternScanUrl = java.util.regex.Pattern.compile("\"(http://magiccards\\.info/scan.*)\"");
    java.util.regex.Matcher m = patternScanUrl.matcher(html);
    if (m.matches()) {
      card.setImageURL(m.group(1));
    }
  }

  private void extractNrArtist(org.mtgdb.model.CardDescription card, final String html) {
    final java.util.regex.Pattern patternCardnumArtist = java.util.regex.Pattern.compile("<b>#(\\d{1,3}[a-z]?)\\s\\((.*)\\)</b>");
    java.util.regex.Matcher m = patternCardnumArtist.matcher(html);
    if (m.find()) {
      card.setNumber(m.group(1));
//      card.setArtist(m.group(2));
    }
  }
}
