package org.mtgdb.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author Sandro Orlando
 */
public final class GrabberJsoup {

  private final String url;
  private final String urlPrefix = "http://magiccards.info/";

  public GrabberJsoup(final String url) {
    this.url = url;
  }

  public static void grabAll(final String url) {
    final GrabberJsoup grabberText = new GrabberJsoup(url);
    Thread thread = new Thread(new Runnable() {
      public void run() {
        try {
          grabberText.grabEdition(url);
        } catch (IOException e) {

        }
      }
    }, "grabAllEditions");
    thread.start();
  }

  private void grabAllEditions() throws IOException {

  }

  private void grabEdition(final String editionUrl) throws IOException {

    Document doc = Jsoup.connect(editionUrl).get();
    Elements tables = doc.select("table .even");
    tables.addAll(doc.select("table .odd"));

    for (Element row : tables) {
      final Elements td = row.select("td");
     // System.out.println("Card: " + td.get(0).text() + " " + td.get(1).text() + " url: " + td.get(1).child(0).attr("href"));

      //System.out.println("\n\nurl: "+urlPrefix+td.get(1).child(0).attr("href")+"\nEdition: "+doc.title()+"\nRarity: "+td.get(4).text()+ "\nName: " +td.get(1).text());
      grabCard(""+urlPrefix+td.get(1).child(0).attr("href"),doc.title(),td.get(4).text(),td.get(1).text());
    }
//    System.out.println(tables);
  }

  private void grabCard(final String cardUrl, final String edition, final String rarity, final String name) throws IOException {

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

    extractTypeLine(card, html);
    extractTypeLineOther(card, html);
    extractTypeLineCreature(card, html);
    extractCardText(card, html);
    extractFlavourText(card, html);
    extractImageURL(card, html);
    extractNrArtist(card, html);
    card.setEdition(edition);
    card.setRarity(rarity);
    card.setName(name);

    System.out.println("=========================================================");
    System.out.println(card.toString());

  }

  private void extractTypeLine(org.mtgdb.model.CardDescription card, final String html) {
    final java.util.regex.Pattern patternTypeline = java.util.regex.Pattern.compile("<p>(.*),\\s\\n\\s*([0-9A-Z]+)\\s\\(([0-9]+)\\)\\n.*</p>");
    java.util.regex.Matcher m = patternTypeline.matcher(html);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setManaCost(m.group(2));
      card.setConvManaCost(Integer.parseInt(m.group(3)));
    }
  }

  private void extractTypeLineOther(org.mtgdb.model.CardDescription card, final String html) {
    final java.util.regex.Pattern patternTypelineOther = java.util.regex.Pattern.compile("<p>(.*)\\s—\\s(.*)\\n.*");
    java.util.regex.Matcher m = patternTypelineOther.matcher(html);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setSubType(m.group(2));
    }
  }

  private void extractTypeLineCreature(org.mtgdb.model.CardDescription card, final String html) {
    final java.util.regex.Pattern patternTypelineCreature = java.util.regex.Pattern.compile("<p>(.*)\\s—\\s(.*)\\s(\\d{1,2}|\\*)/(\\d{1,2}|\\*),\\s\\n\\s*([0-9A-Z]+)\\s\\(([0-9]+)\\)\\n.*</p>");
    java.util.regex.Matcher m = patternTypelineCreature.matcher(html);
    if (m.matches()) {
      card.setType(m.group(1));
      card.setSubType(m.group(2));
      card.setPower(Integer.parseInt(m.group(3)));
      card.setHp(Integer.parseInt(m.group(4)));
      card.setManaCost(m.group(5));
      card.setConvManaCost(Integer.parseInt(m.group(6)));
    }
  }

  private void extractCardText(org.mtgdb.model.CardDescription card, final String html) {
    final java.util.regex.Pattern patternCardText = java.util.regex.Pattern.compile("<p class=\"ctext\"><b>(.*)</b></p>");
    java.util.regex.Matcher m = patternCardText.matcher(html);
    if (m.matches()) {
      card.setCardText(m.group(1));
    }
  }

  private void extractFlavourText(org.mtgdb.model.CardDescription card, final String html) {
    final java.util.regex.Pattern patternFlavorText = java.util.regex.Pattern.compile("<p><i>(.*)</i></p>");
    java.util.regex.Matcher m = patternFlavorText.matcher(html);
    if (m.matches()) {
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
    final java.util.regex.Pattern patternCardnumArtist = java.util.regex.Pattern.compile("#(\\d{1,3})\\s\\((.*)\\)");
    java.util.regex.Matcher m = patternCardnumArtist.matcher(html);
    if (m.matches()) {
      card.setNumber(m.group(1));
      card.setArtist(m.group(2));
    }
  }
}
