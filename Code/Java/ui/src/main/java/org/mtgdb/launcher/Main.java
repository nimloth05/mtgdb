package org.mtgdb.launcher;

import org.mtgdb.grabber.GrabberJsoup;

/**
 * @author Sandro Orlando
 */
public final class Main {

  public static void main(String[] args) {
    GrabberJsoup.grabAll("http://magiccards.info/m14/en.html");
  }
}
