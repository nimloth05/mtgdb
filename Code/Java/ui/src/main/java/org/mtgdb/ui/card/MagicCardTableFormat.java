package org.mtgdb.ui.card;

import ca.odell.glazedlists.gui.TableFormat;
import org.mtgdb.model.MagicCard;

/**
 * @author Sandro Orlando
 */
public final class MagicCardTableFormat implements TableFormat<MagicCard> {

  private ColumnDescription[] columnDescriptions = new ColumnDescription[]{
    new ColumnDescription("Edition", String.class),
    new ColumnDescription("Name", String.class),
    new ColumnDescription("Type", String.class),
    new ColumnDescription("Sub Type", String.class),
    new ColumnDescription("Rarity", String.class),
    new ColumnDescription("Mana Cost", String.class),
    new ColumnDescription("Converted Mana Cost", int.class),
    new ColumnDescription("power", int.class),
    new ColumnDescription("toughness", int.class),
    new ColumnDescription("Text", String.class),
    new ColumnDescription("Flavor", String.class),
    new ColumnDescription("Artist", String.class),
    new ColumnDescription("Card Number", String.class),

  };

  @Override
  public int getColumnCount() {
    return columnDescriptions.length;
  }

  @Override
  public String getColumnName(final int i) {
    return columnDescriptions[i].name;
  }

  @Override
  public Object getColumnValue(final MagicCard card, final int columnIndex) {
    switch (columnIndex) {
      case 0:
        return card.getEdition().getName();
      case 1:
        return card.getName();
      case 2:
        return card.getType();
      case 3:
        return card.getSubType();
      case 4:
        return card.getRarity();
      case 5:
        return card.getManaCost();
      case 6:
        return card.getConvertedManaCost();
      case 7:
        return card.getPower();
      case 8:
        return card.getToughness();
      case 9:
        return card.getText();
      case 10:
        return card.getFlavorText();
      case 11:
        return card.getArtist();
      case 12:
        return card.getNumber();
      default:throw new IllegalArgumentException("unknown column");
    }
  }

  private static class ColumnDescription {

    public String name;
    public Class<?> type;

    public ColumnDescription(final String name, final Class<?> type) {
      this.name = name;
      this.type = type;
    }
  }
}
