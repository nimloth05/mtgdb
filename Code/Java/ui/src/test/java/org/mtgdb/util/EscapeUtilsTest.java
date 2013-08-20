package org.mtgdb.util;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Sandro Orlando
 */
public final class EscapeUtilsTest {

  @Test
  public void testEscapeSql() {
    Assert.assertEquals("Hallo", EscapeUtils.escapeSQL("Hallo"));
    Assert.assertEquals("Sandro''s Test", EscapeUtils.escapeSQL("Sandro's Test"));
  }

}
