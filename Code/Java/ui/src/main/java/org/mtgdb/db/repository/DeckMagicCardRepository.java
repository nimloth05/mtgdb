package org.mtgdb.db.repository;

import com.google.inject.Inject;
import com.j256.ormlite.stmt.DeleteBuilder;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.model.Deck;
import org.mtgdb.model.DeckMagicCard;

import java.sql.SQLException;
import java.util.Collection;

/**
 * @author Sandro Orlando
 */
public final class DeckMagicCardRepository extends AbstractRepository<DeckMagicCard, Integer> implements IDeckMagicCardRepository {

  @Inject
  public DeckMagicCardRepository(final IDatabaseConnection connection) {
    super(connection);
  }

  @Override
  protected Class<DeckMagicCard> getClassLiteral() {
    return DeckMagicCard.class;
  }

  @Override
  public Collection<DeckMagicCard> getForDeck(final Deck deck) {
    try {
      return dao.queryForEq(DeckMagicCard.DECK_ID_FIELD_NAME, deck.getId());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(final int id, final String id1) {
    try {
      final DeleteBuilder<DeckMagicCard,Integer> deleteBuilder = dao.deleteBuilder();
      deleteBuilder.where().eq(DeckMagicCard.DECK_ID_FIELD_NAME, id).and().eq(DeckMagicCard.MAGIC_CARD_ID_FIELD_NAME, id1);
      dao.delete(deleteBuilder.prepare());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
