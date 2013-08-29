package org.mtgdb.grabber;

import junit.framework.Assert;
import org.apache.commons.lang.mutable.MutableInt;
import org.junit.Test;

/**
 * @author Sandro Orlando
 */
public final class EditionGrabberTest {

  @Test
  public void testGrabEditions() {
    EditionGrabber grabber = new EditionGrabber("English");
    final MutableInt counter = new MutableInt(0);
    final String[] actualUrl = new String[1];
    final String[] actualEditionId = new String[1];

    grabber.startGrabbing(new IEditionGrabberListener() {

      @Override
      public void grab(final String url, final String editionId) {
        counter.increment();
        actualUrl[0] = url;
        actualEditionId[0] = editionId;
      }
    });
    Assert.assertEquals("http://magiccards.info//dcilm/en.html", actualUrl[0]);
    Assert.assertEquals("dcilm", actualEditionId[0]);
    Assert.assertTrue(counter.intValue() >= 156);
  }

}
