package org.mtgdb.db;

import org.mtgdb.model.CardDescription;
import org.mtgdb.model.ILibraryCard;
import org.mtgdb.util.Constants;

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

  public ILibraryCard getAllLibraryCards() {
//    connection.executeQuery("");
    return null;
  }

  public void saveAllCardDescription(final List<CardDescription> allCards) {
    StringBuilder builder = new StringBuilder();
    builder.append("insert into \"CardDescription\" values ");
    for (CardDescription cardDescription : allCards) {
      builder.append(Constants.LEFT_PARENTHESIS)
      .append("'" +  cardDescription.getEdition() + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getType() + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getSubType() + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getManaCost() + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getConvManaCost() + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getPower() + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getHp() + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getImageURL() + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getCardText().replace("\"", "") + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getFlavorText().replace("\"", "") + "'")
      .append(Constants.COMMA + "'" + "0" + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getArtist() + "'")
      .append(Constants.COMMA + "'" + "0" + "'")
      .append(Constants.COMMA +"'" +  cardDescription.getName() + "'")
      .append(Constants.COMMA +"'" +   "'")
      .append(Constants.RIGHT_PARENTHESIS);
      System.out.println(cardDescription);
    }

    System.out.println("sql: \n" + builder.toString());
    connection.executeSql(builder.toString());
  }
}
