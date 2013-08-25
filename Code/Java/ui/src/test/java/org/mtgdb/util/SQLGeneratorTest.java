package org.mtgdb.util;

import junit.framework.Assert;
import org.junit.Test;
import org.mtgdb.db.sql.Column;
import org.mtgdb.db.sql.SQLGenerator;
import org.mtgdb.db.sql.Value;

/**
 * @author Sandro Orlando
 */
public final class SQLGeneratorTest {

  @Test
  public void testInsertInto() {
    final String actual = SQLGenerator.insertInto("table1",
      new Column[]{new Column("spalte 1"), new Column("spalte 2")},
      new Value[][]{{new Value("wert 1"), new Value("wert 2")}});

    final String expected = "insert into \"table1\" (\"spalte 1\",\"spalte 2\") values ('wert 1','wert 2')";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testInsertIntoWithInt() {
    final String actual = SQLGenerator.insertInto("table1",
      new Column[]{new Column("spalte 1"), new Column("spalte 2")},
      new Value[][]{{new Value("wert 1"), new Value(1)}});
    final String expected = "insert into \"table1\" (\"spalte 1\",\"spalte 2\") values ('wert 1',1)";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMultipleInsert() {
    final String actual = SQLGenerator.insertInto("table1",
      new Column[]{new Column("spalte 1"), new Column("spalte 2")},
      new Value[][]{new Value[]{new Value("wert 1"), new Value(1)},
        new Value[]{new Value("wert 2"), new Value(2)}});

    final String expected = "insert into \"table1\" (\"spalte 1\",\"spalte 2\") values ('wert 1',1),('wert 2',2)";
    Assert.assertEquals(expected, actual);
  }
}
