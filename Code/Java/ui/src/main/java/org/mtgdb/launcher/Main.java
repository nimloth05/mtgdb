package org.mtgdb.launcher;

import org.mtgdb.grabber.GrabberJsoup;
import org.mtgdb.model.CardDescription;
import org.mtgdb.grabber.IGrabberListener;

import java.io.IOException;

/**
 * @author Sandro Orlando
 */
public final class Main {

  public static void main(String[] args) throws IOException {
    GrabberJsoup grab = new GrabberJsoup("");
    grab.grabAllEditions(new IGrabberListener() {
      @Override
      public void grabbed(final CardDescription description) {

      }
    });
  }
}
