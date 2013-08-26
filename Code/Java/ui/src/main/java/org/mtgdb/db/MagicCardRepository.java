package org.mtgdb.db;

import com.google.inject.Inject;
import org.mtgdb.db.sql.Column;
import org.mtgdb.db.sql.SQLGenerator;
import org.mtgdb.db.sql.Value;
import org.mtgdb.model.CardDescription;
import org.mtgdb.model.Rarity;
import org.mtgdb.util.Constants;
import org.mtgdb.util.assertion.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class MagicCardRepository extends AbstractRepository implements IMagicCardRepository {

  private static final Column[] columns = new Column[]{
    new Column("REF_EDITION"),
    new Column("cardNumber"),
    new Column("type"),
    new Column("subType"),
    new Column("manaCost"),
    new Column("convManaCost"),
    new Column("power"),
    new Column("toughness"),
    new Column("imageURL"),
    new Column("cardText"),
    new Column("flavorText"),
    new Column("artist"),
    new Column("rarity"),
    new Column("name"),
    new Column("cardId")
  };
  private final IDatabaseConnection connection;

  @Inject
  public MagicCardRepository(final IDatabaseConnection connection) {
    this.connection = connection;
  }

  @Override
  public void saveAll(final ITransaction transaction, final Collection<CardDescription> cards) {
    Value[][] rows = new Value[cards.size()][];
    int index = 0;
    for (CardDescription card : cards) {
      Value[] row = new Value[]{
        new Value(card.getEdition()),
        new Value(card.getNumber()),
        new Value(card.getType()),
        new Value(card.getSubType()),
        new Value(card.getManaCost()),
        new Value(card.getConvManaCost()),
        new Value(card.getPower()),
        new Value(card.getToughness()),
        new Value(card.getImageURL()),
        new Value(card.getCardText()),
        new Value(card.getFlavorText()),
        new Value(card.getArtist()),
        new Value(card.getRarity().ordinal()),
        new Value(card.getName()),
        new Value("")
      };
      rows[index++] = row;
      card.setCardId(card.getEdition() + Constants.UNDERSCORE + card.getNumber());
    }
    final String sql = SQLGenerator.insertInto(DBConstants.MAGIC_CARD_TABLE, columns, rows);
    transaction.insert(sql);
  }

  @Override
  public void deleteAll(final ITransaction transaction) {
    transaction.execute("truncate table \"" + DBConstants.MAGIC_CARD_TABLE + "\"");
  }

  @Override
  public List<CardDescription> getAllCards() {
    List<CardDescription> cards = new ArrayList<>();
    try {
      ResultSet rs = connection.executeQuery("SELECT * FROM \"" + DBConstants.MAGIC_CARD_TABLE + "\";");
      while (rs.next()) {
        String edition = rs.getString("REF_EDITION");
        String cardName = rs.getString("name");
        String cardType = rs.getString("type");
        String cardSubtype = rs.getString("subType");
        int convertedManaCost = rs.getInt("convManaCost");
        String manaCost = rs.getString("manaCost");
        int power = rs.getInt("power");
        int toughness = rs.getInt("toughness");
        String imgUrl = rs.getString("imageURL");
        String cardText = rs.getString("cardText");
        String flavorText = rs.getString("flavorText");
        String artist = rs.getString("artist");
        int rarity = rs.getInt("rarity");
        String cardId = rs.getString("cardId");
        String cardNum = rs.getString("cardNumber");
        CardDescription description = new CardDescription();
        cards.add(description);
        description.setEdition(edition);
        description.setNumber(cardNum);
        description.setType(cardType);
        description.setSubType(cardSubtype);
        description.setManaCost(manaCost);
        description.setConvManaCost(convertedManaCost);
        description.setPower(power);
        description.setToughness(toughness);
        description.setImageURL(imgUrl);
        description.setCardText(cardText);
        description.setFlavorText(flavorText);
        description.setArtist(artist);
        description.setRarity(Rarity.values()[rarity]);
        description.setName(cardName);
        description.setCardId(cardId);
      }

    } catch (SQLException e) {
      Assert.log(e);
    }
    return cards;
  }

}
