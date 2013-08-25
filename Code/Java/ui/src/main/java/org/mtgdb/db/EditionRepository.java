package org.mtgdb.db;

import org.mtgdb.db.sql.Column;
import org.mtgdb.db.sql.SQLGenerator;
import org.mtgdb.model.Edition;

/**
 * @author Sandro Orlando
 */
public final class EditionRepository extends AbstractRepository {

  public void save(final ITransaction transaction, final Edition edition) {
    final String sql = SQLGenerator.insertInto(DBConstants.EDITION_TABLE,
      new Column(DBConstants.EDITION_ID, edition.getEditionId()),
      new Column(DBConstants.EDITION_NAME, edition.getEdition()),
      new Column(DBConstants.EDITION_NUMBER_OF_CARDS, edition.getNumberOfCards()));
     transaction.insert(sql);
  }

}
