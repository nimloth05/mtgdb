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
  private static final String SITEMAP = "http://magiccards.info/sitemap.html";

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
    Document doc = Jsoup.connect(SITEMAP).get();
    Elements table = doc.select(":containsOwn(" + language + ") + table");
    Elements lists = table.select("tr > td > ul");
    for (Element list : lists) {
      Elements items = list.getAllElements();
      Elements editions = items.select("a");

      for (Element edition : editions) {
        if (edition.tagName().equals("a")) {
          final String editionId = edition.nextElementSibling().text();
          final String urlSuffix = edition.attr("href");
          listener.grab(Grabber.urlPrefix + urlSuffix, editionId);
        }
        break;
      }
      break;
    }
  }
}
