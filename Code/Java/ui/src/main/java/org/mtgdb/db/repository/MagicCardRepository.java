package org.mtgdb.db.repository;

import com.google.inject.Inject;
import com.j256.ormlite.dao.GenericRawResults;
import org.mtgdb.db.DBConstants;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.model.Edition;
import org.mtgdb.model.MagicCard;
import org.mtgdb.util.assertion.Assert;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
  public void deleteAll() {
    try {
      dao.executeRaw("truncate table \"" + DBConstants.MAGIC_CARD_TABLE + "\"");
      dao.executeRaw("CALL FTL_DROP_INDEX('PUBLIC', '"+DBConstants.MAGIC_CARD_TABLE+"');");
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

  @Override
  public List<MagicCard> searchFreeText(final String text) {
    try {
      final GenericRawResults<String[]> strings = dao.queryRaw("SELECT * FROM FTL_SEARCH_DATA('"+ text+"', 0, 0);");
      List<MagicCard> result = new LinkedList<>();
      for (String[] string : strings) {
        String id = string[3].substring(1, string[3].length()-1);
        result.add(dao.queryForId(id));
      }
      return result;
    } catch (SQLException e) {
      Assert.log(e);
      return Collections.emptyList();
    }
  }

  @Override
  public void enableLuceneIndex() {
    try {
      dao.executeRaw("CALL FTL_CREATE_INDEX('PUBLIC', '"+DBConstants.MAGIC_CARD_TABLE+"', NULL);");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void setId(final MagicCard obj) {
    obj.setId();
  }
}
