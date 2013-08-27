package org.mtgdb.db.repository;

import com.google.inject.Inject;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.ITransactionToken;
import org.mtgdb.model.PhysicalCard;

import java.sql.SQLException;

/**
 * @author Sandro Orlando
 */
public final class PhysicalCardRepository extends AbstractRepository<PhysicalCard, Integer> implements IPhysicalCardRepository {

  @Inject
  public PhysicalCardRepository(final IDatabaseConnection connection) {
    super(connection);
  }

  public void save(final ITransactionToken transaction, final PhysicalCard card) {
    try {
      dao.create(card);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Class<PhysicalCard> getClassLiteral() {
    return PhysicalCard.class;
  }
}
