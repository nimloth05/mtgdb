package org.mtgdb.db;

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
public final class MagicCardRepository extends AbstractRepository implements IRepository {


  private final IDatabaseConnection connection;

  public MagicCardRepository(final IDatabaseConnection connection) {
    this.connection = connection;
  }

  public void saveAll(final ITransaction transaction, final Collection<CardDescription> cards) {
    StringBuilder builder = new StringBuilder();
    builder.append("insert into \"" + DBConstants.MAGIC_CARD_TABLE + "\" values ");
    for (CardDescription cardDescription : cards) {
      builder.append(Constants.LEFT_PARENTHESIS)
        .append("'" + cardDescription.getEdition() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getNumber() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getType() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getSubType() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getManaCost() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getConvManaCost() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getPower() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getToughness() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getImageURL() + "'")
        .append(Constants.COMMA + "'" + escape(cardDescription.getCardText()) + "'")
        .append(Constants.COMMA + "'" + escape(cardDescription.getFlavorText()) + "'")
        .append(Constants.COMMA + "'" + cardDescription.getArtist() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getRarity().ordinal() + "'")
        .append(Constants.COMMA + "'" + escape(cardDescription.getName()) + "'")
        .append(Constants.COMMA + "'" + "'")
        .append(Constants.RIGHT_PARENTHESIS);

      cardDescription.setCardId(cardDescription.getEdition()+Constants.UNDERSCORE+cardDescription.getNumber());

      builder.append(Constants.COMMA);
    }
    builder.deleteCharAt(builder.length() - 1);
    transaction.insert(builder.toString());
  }

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
