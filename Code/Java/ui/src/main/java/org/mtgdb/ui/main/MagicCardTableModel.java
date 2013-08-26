package org.mtgdb.ui.main;

import com.google.inject.Inject;
import org.mtgdb.db.repository.MagicCardRepository;
import org.mtgdb.model.MagicCard;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * @author Michael Sacher
 */
public class MagicCardTableModel implements TableModel {

  private final List<MagicCard> cards;
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

  @Inject
  public MagicCardTableModel(MagicCardRepository repository) {
    cards = repository.getAllCards();
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

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    final MagicCard magicCard = cards.get(rowIndex);
    switch (columnIndex) {
      case 0: return magicCard.getEdition();
      case 1: return magicCard.getName();
      case 2: return magicCard.getType();
      case 3: return magicCard.getSubType();
      case 4: return magicCard.getRarity();
      case 5: return magicCard.getManaCost();
      case 6: return magicCard.getConvManaCost();
      case 7: return magicCard.getPower();
      case 8: return magicCard.getToughness();
      case 9: return magicCard.getCardText();
      case 10: return magicCard.getFlavorText();
      case 11: return magicCard.getArtist();
      case 12: return magicCard.getNumber();
    }
    return magicCard.getCardId();
  }

  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
  }

  public void addTableModelListener(TableModelListener l) {
  }

  public void removeTableModelListener(TableModelListener l) {
  }

  public MagicCard getCard(final int index) {
    return cards.get(index);
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
