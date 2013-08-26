package org.mtgdb.db.repository;

import org.mtgdb.db.ITransaction;
import org.mtgdb.db.sql.Column;
import org.mtgdb.db.sql.SQLGenerator;
import org.mtgdb.db.sql.Value;
import org.mtgdb.model.PhysicalCard;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sandro Orlando
 */
public final class PhysicalCardRepository extends AbstractRepository{

  private static final Column[] columns = new Column[] {
    new Column("REF_CARD"),
    new Column("REF_CONTAINER"),
    new Column("CONDITION"),
    new Column("LANG")
  };

  public void save(final ITransaction transaction, final PhysicalCard card) {
    final String sql = SQLGenerator.insertInto("PhysicalCard", columns, new Value[][]{
      {new Value(card.getCardId()), new Value(card.getContainerId()), new Value(card.getCondition().ordinal()), new Value(card.getLanguage())}
    });
    ResultSet rs = transaction.insert(sql);
    try {
      while(rs.next()) {
        card.setId(rs.getInt(1));
      }
      rs.close();
    } catch (SQLException e) {
      throw new RuntimeException("cannot set id",e);
    }
  }
}
