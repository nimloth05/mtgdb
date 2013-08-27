package org.mtgdb.db.repository;

import org.mtgdb.db.DBConstants;
import org.mtgdb.db.DatabaseConnection;
import org.mtgdb.db.ITransactionToken;
import org.mtgdb.model.Edition;

import javax.inject.Inject;
import java.sql.SQLException;

/**
 * @author Sandro Orlando
 */
public final class EditionRepository extends AbstractRepository<Edition, String> implements IEditionRepository {

  @Inject
  public EditionRepository(final DatabaseConnection connection) {
    super(connection);
  }

  @Override
  public void save(final ITransactionToken transaction, final Edition edition) {
    try {
      dao.create(edition);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void deleteAll(final ITransactionToken transaction) {
    try {
      dao.executeRaw("truncate table \"" + DBConstants.EDITION_TABLE + "\"");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Class<Edition> getClassLiteral() {
    return Edition.class;
  }
}
