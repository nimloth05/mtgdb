package org.mtgdb.grabber;

import org.apache.commons.lang.mutable.MutableBoolean;
import org.apache.commons.lang.mutable.MutableInt;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sandro Orlando
 */
public final class CardListGrabberTest {

  @Test
  public void testGrab() {
    final MutableBoolean beginCalled = new MutableBoolean(false);
    final MutableBoolean endCalled = new MutableBoolean(false);
    final MutableInt counter = new MutableInt(0);
    CardListGrabber grabber = new CardListGrabber(new ICardListGrabberListener() {

      @Override
      public void begin(final String name, final String editionId, final int numberOfCards) {
        beginCalled.setValue(true);
      }

      @Override
      public void grabCard(final String cardUrl, final String rarity, final String name) {
        counter.increment();
      }

      @Override
      public void end() {
        endCalled.setValue(true);
      }
    });

    grabber.grab("http://magiccards.info/dgm/en.html", "dgm");

    Assert.assertTrue(beginCalled.booleanValue());
    Assert.assertTrue(beginCalled.booleanValue());
    Assert.assertEquals(171, counter.intValue());
  }

}
