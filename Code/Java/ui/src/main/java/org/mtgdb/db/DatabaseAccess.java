package org.mtgdb.db;

import org.mtgdb.model.MagicCard;
import org.mtgdb.model.CardDescription;
import org.mtgdb.model.Container;
import org.mtgdb.model.ILibraryCard;
import org.mtgdb.util.Constants;
import org.mtgdb.util.EscapeUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class DatabaseAccess {

  private IDatabaseConnection connection;

  public DatabaseAccess(final IDatabaseConnection connection) {
    this.connection = connection;
  }

  public void saveCardDescription(final CardDescription cardDescription) {

//    String sql = "INSERT INTO CardDescription VALUES("
//                      + "\"" +  cardDescription.getEdition() + "\"" +
//      Constants.COMMA + cardDescription.getType() +
//      Constants.COMMA + cardDescription.getSubType() +
//      Constants.COMMA + cardDescription.getManaCost() +
//      Constants.COMMA + cardDescription.getConvManaCost() +
//      Constants.COMMA + cardDescription.getPower() +
//      Constants.COMMA + cardDescription.getHp() +
//      Constants.COMMA + cardDescription.getImageURL() +
//      Constants.COMMA + cardDescription.getCardText() +
//      Constants.COMMA + cardDescription.getFlavorText() +
//      Constants.COMMA + cardDescription.getArtist() +
//      Constants.COMMA + "0" +
//      Constants.COMMA + cardDescription.getName() +
//    connection.execute()
  }

  public Collection<ILibraryCard> getAllLibraryCards() {

    return null;
  }

  public ArrayList<MagicCard> getAllCards() {
    ArrayList<MagicCard> cards = new ArrayList<MagicCard>();
    try {
      ResultSet rs = connection.executeQuery("SELECT * FROM \"CardDescription\";");
      while (rs.next()) {
        String edition = rs.getString("REF_EDITION");
        String cardName = rs.getString("name");
        String cardType = rs.getString("type");
        String cardSubtype = rs.getString("subType");
        int convertedManaCost = rs.getInt("convManaConst");
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
        cards.add(new MagicCard(
          edition,
          cardNum,
          cardType,
          cardSubtype,
          manaCost,
          convertedManaCost,
          power,
          toughness,
          imgUrl,
          cardText,
          flavorText,
          artist,
          rarity,
          cardName,
          cardId
        ));
      }

    } catch (SQLException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    return cards;
  }

  public void saveAllCardDescription(final List<CardDescription> allCards) {
    StringBuilder builder = new StringBuilder();
    builder.append("insert into \"CardDescription\" values ");
    for (CardDescription cardDescription : allCards) {
      builder.append(Constants.LEFT_PARENTHESIS)
        .append("'" + cardDescription.getEdition() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getNumber() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getType() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getSubType() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getManaCost() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getConvManaCost() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getPower() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getHp() + "'")
        .append(Constants.COMMA + "'" + cardDescription.getImageURL() + "'")
        .append(Constants.COMMA + "'" + escape(cardDescription.getCardText()) + "'")
        .append(Constants.COMMA + "'" + escape(cardDescription.getFlavorText()) + "'")
        .append(Constants.COMMA + "'" + cardDescription.getArtist() + "'")
        .append(Constants.COMMA + "'" + "0" + "'")
        .append(Constants.COMMA + "'" + escape(cardDescription.getName()) + "'")
        .append(Constants.COMMA + "'" + "'")
        .append(Constants.RIGHT_PARENTHESIS);

      builder.append(Constants.COMMA);
    }
    builder.deleteCharAt(builder.length() - 1);

    System.out.println("sql: \n" + builder.toString());
    connection.executeSql(builder.toString());
  }

  private String escape(String value) {
    return EscapeUtils.escapeSQL(value);
  }

  public void closeDB() {
    connection.closeDB();
  }

  public void saveContainer(final Container container) {
    StringBuilder builder = new StringBuilder();
    builder.append("insert into \"Container\" ")
      .append("(NAME, DESCRIPTION) values (")
      .append("'" + escape(container.getName()) + "'")
      .append(Constants.COMMA + "'" + escape(container.getDescription()) + "'")
      .append(Constants.RIGHT_PARENTHESIS);
    System.out.println("sql: \n" + builder.toString());
    connection.executeSql(builder.toString());
  }

}
