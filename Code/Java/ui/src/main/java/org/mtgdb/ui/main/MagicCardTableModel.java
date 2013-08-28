package org.mtgdb.ui.main;

import org.mtgdb.model.IMagicCard;
import org.mtgdb.model.MagicCard;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author Michael Sacher
 */
public class MagicCardTableModel extends AbstractTableModel {

  private List<MagicCard> cards;
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

  public MagicCardTableModel(List<MagicCard> cards) {
    this.cards = cards;
  }

  public int getRowCount() {
    return cards.size();
  }

  public int getColumnCount() {
    return columnDescriptions.length;
  }

  public String getColumnName(int columnIndex) {
    return columnDescriptions[columnIndex].name;
  }

  public Class<?> getColumnClass(int columnIndex) {
    return columnDescriptions[columnIndex].type;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    final IMagicCard IMagicCard = cards.get(rowIndex);
    switch (columnIndex) {
      case 0: return IMagicCard.getEdition().getName();
      case 1: return IMagicCard.getName();
      case 2: return IMagicCard.getType();
      case 3: return IMagicCard.getSubType();
      case 4: return IMagicCard.getRarity();
      case 5: return IMagicCard.getManaCost();
      case 6: return IMagicCard.getConvertedManaCost();
      case 7: return IMagicCard.getPower();
      case 8: return IMagicCard.getToughness();
      case 9: return IMagicCard.getText();
      case 10: return IMagicCard.getFlavorText();
      case 11: return IMagicCard.getArtist();
      case 12: return IMagicCard.getNumber();
    }
    return IMagicCard.getId();
  }

  public IMagicCard getCard(final int index) {
    return cards.get(index);
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  public void updateData(final List<MagicCard> magicCards) {
    this.cards = magicCards;
    fireTableDataChanged();
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
