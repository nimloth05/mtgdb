package org.mtgdb.db.repository;

import com.google.inject.Inject;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.model.CardMemory;
import org.mtgdb.model.MagicCard;

import java.sql.SQLException;

/**
 * @author Sandro Orlando
 */
public final class CardMemoryRepository extends AbstractRepository<CardMemory, Integer> implements ICardMemoryRepository {

  @Inject
  public CardMemoryRepository(final IDatabaseConnection connection) {
    super(connection);
  }

  @Override
  protected Class<CardMemory> getClassLiteral() {
    return CardMemory.class;
  }

  @Override
  public void rememberCard(final MagicCard currentCard) {
    try {
      final CardMemory data = new CardMemory();
      data.setMagicCard(currentCard);
      dao.create(data);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
