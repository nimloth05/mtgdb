package org.mtgdb.db.repository;

import org.mtgdb.db.DBConstants;
import org.mtgdb.db.ITransaction;
import org.mtgdb.db.sql.Column;
import org.mtgdb.db.sql.SQLGenerator;
import org.mtgdb.db.sql.Value;
import org.mtgdb.model.Edition;

/**
 * @author Sandro Orlando
 */
public final class EditionRepository extends AbstractRepository implements IEditionRepository {

  private static final Column[] columns = new Column[]{
    new Column(DBConstants.EDITION_ID),
    new Column(DBConstants.EDITION_NAME),
    new Column(DBConstants.EDITION_NUMBER_OF_CARDS)
  };

  @Override
  public void save(final ITransaction transaction, final Edition edition) {
    final String sql = SQLGenerator.insertInto(DBConstants.EDITION_TABLE, columns,
      new Value[][]{{
        new Value(edition.getEditionId()),
        new Value(edition.getEdition()),
        new Value(edition.getNumberOfCards())}});
    transaction.insert(sql);
  }

  @Override
  public void deleteAll(final ITransaction transaction) {
    transaction.execute("truncate table \"" + DBConstants.EDITION_TABLE + "\"");
  }

}
