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

  public GrabberJsoup(final String url) {
    this.url = url;
  }

  public static void grabAll(final String url) {
    final GrabberJsoup grabberText = new GrabberJsoup(url);
    Thread thread = new Thread(new Runnable() {
          public void run() {
            try {
              grabberText.grab();
            } catch (IOException e) {

            }
          }
        }, "grabberText");
    thread.start();
  }

  private void grab() throws IOException {
    Document doc = Jsoup.connect(url).get();
    Elements tables = doc.select("table .even");
    for (Element row : tables) {
      final Elements td = row.select("td");
      System.out.println("Card: " + td.get(0).text() + " " + td.get(1).text());
    }
  }

  private void handleContent(final StringBuilder builder) {

  }}
