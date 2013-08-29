package org.mtgdb.launcher;

import org.mtgdb.grabber.Grabber;
import org.mtgdb.grabber.IGrabberListener;
import org.mtgdb.model.Edition;
import org.mtgdb.model.MagicCard;

import java.io.IOException;

/**
 * @author Sandro Orlando
 */
public final class Main {

  public static void main(String[] args) throws IOException {
    Grabber grab = new Grabber("");
    grab.grabAllEditions(new IGrabberListener() {
      @Override
      public void beginEdition(final Edition edition) {
      }

      @Override
      public void grabbed(final MagicCard description) {

      }

      @Override
      public void endEdition() {
      }
    });
  }
}
