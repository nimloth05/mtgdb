package org.mtgdb.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mtgdb.model.Edition;
import org.mtgdb.model.IMagicCard;
import org.mtgdb.model.MagicCard;
import org.mtgdb.model.Rarity;
import org.mtgdb.util.Constants;
import org.mtgdb.util.assertion.Assert;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sandro Orlando
 */
public final class GrabberJsoup {

  public static final String urlPrefix = "http://magiccards.info/";
  private final String lang;

  public GrabberJsoup(final String lang) {
    this.lang = lang;
  }

  public void grabAllEditions(final IGrabberListener listener) throws IOException {
    boolean repeat = false;
    int counter = 0;
    do {
      try {
        doGrab(listener);
      } catch (IOException e) {
        Assert.log(e);
        ++counter;
        repeat = counter < 3;
      }
    } while (repeat);
  }




}
