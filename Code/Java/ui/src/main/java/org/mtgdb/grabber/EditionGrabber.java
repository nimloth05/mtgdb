package org.mtgdb.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mtgdb.util.assertion.Assert;

import java.io.IOException;

/**
 * @author Sandro Orlando
 */
public final class EditionGrabber {

  private final String language;

  public EditionGrabber(final String language) {
    this.language = language;
  }

  public void startGrabbing(final IEditionGrabberListener listener) {
    try {
      doGrab(listener);
    } catch (IOException e) {
      Assert.log(e);
    }
  }

  private void doGrab(final IEditionGrabberListener listener) throws IOException {
    final String sitemap = "http://magiccards.info/sitemap.html";
    Document doc = Jsoup.connect(sitemap).get();
    Elements table = doc.select(":containsOwn(" + language + ") + table");
    Elements lists = table.select("tr > td > ul");
    for (Element list : lists) {
      Elements items = list.getAllElements();
      Elements editions = items.select("a");

      for (Element edition : editions) {
        if (edition.tagName().equals("a")) {
          final String editionId = edition.nextElementSibling().text();
          final String urlSuffix = edition.attr("href");
          listener.grab(GrabberJsoup.urlPrefix + urlSuffix, editionId);
        }
      }
    }
  }
}
