package org.mtgdb.db.repository;

import com.google.inject.Inject;
import org.mtgdb.db.DBConstants;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.ITransactionToken;
import org.mtgdb.model.Edition;
import org.mtgdb.model.MagicCard;

import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * @author Sandro Orlando
 */
public final class MagicCardRepository extends AbstractRepository<MagicCard, String> implements IMagicCardRepository {

  @Inject
  public MagicCardRepository(final IDatabaseConnection connection) {
    super(connection);
  }

  @Override
  protected Class<MagicCard> getClassLiteral() {
    return MagicCard.class;
  }

  @Override
  public void saveAll(final ITransactionToken transaction, final Collection<MagicCard> cards) {
    try {
      dao.callBatchTasks(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
          for (MagicCard card : cards) {
            card.setId();
            dao.create(card);
          }
          return null;
        }
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
//    Value[][] rows = new Value[cards.size()][];
//    int index = 0;
//    for (IMagicCard card : cards) {
//      Value[] row = new Value[]{
//        new Value(card.getEdition()),
//        new Value(card.getNumber()),
//        new Value(card.getType()),
//        new Value(card.getSubType()),
//        new Value(card.getManaCost()),
//        new Value(card.getConvertedManaCost()),
//        new Value(card.getPower()),
//        new Value(card.getToughness()),
//        new Value(card.getImageURL()),
//        new Value(card.getText()),
//        new Value(card.getFlavorText()),
//        new Value(card.getArtist()),
//        new Value(card.getRarity().ordinal()),
//        new Value(card.getName()),
//        new Value("")
//      };
//      rows[index++] = row;
//      ((MagicCard)card).setCardId(card.getEdition() + Constants.UNDERSCORE + card.getNumber());
//    }
//    final String sql = SQLGenerator.insertInto(DBConstants.MAGIC_CARD_TABLE, columns, rows);
//    transaction.insert(sql);
  }

  @Override
  public void deleteAll(final ITransactionToken transaction) {
    try {
      dao.executeRaw("truncate table \"" + DBConstants.MAGIC_CARD_TABLE + "\"");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public MagicCard getCard(final Edition edition, final String cardNumber) {
    try {
      return dao.queryForId(MagicCard.constructId(edition, cardNumber));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
