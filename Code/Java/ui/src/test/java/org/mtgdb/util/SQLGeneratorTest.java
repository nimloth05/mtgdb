package org.mtgdb.util;

import junit.framework.Assert;
import org.junit.Test;
import org.mtgdb.db.sql.Column;
import org.mtgdb.db.sql.SQLGenerator;

/**
 * @author Sandro Orlando
 */
public final class SQLGeneratorTest {

  @Test
  public void testInsertInto() {
    final String actual = SQLGenerator.insertInto("table1", new Column("spalte 1", "wert 1"), new Column("spalte 2", "wert 2"));
    final String expected = "insert into \"table1\" (\"spalte 1\",\"spalte 2\") values ('wert 1','wert 2')";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testInsertIntoWithInt() {
    final String actual = SQLGenerator.insertInto("table1", new Column("spalte 1", "wert 1"), new Column("spalte 2", 1));
    final String expected = "insert into \"table1\" (\"spalte 1\",\"spalte 2\") values ('wert 1',1)";
    Assert.assertEquals(expected, actual);
  }

}
