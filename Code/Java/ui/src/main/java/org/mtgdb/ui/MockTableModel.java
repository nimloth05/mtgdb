package org.mtgdb.ui;

import org.mtgdb.db.MagicCardRepository;
import org.mtgdb.model.CardDescription;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * @author Michael Sacher
 */
public class MockTableModel implements TableModel {

  private final List<CardDescription> cards;
  private MagicCardRepository repository;
  private ColumnDescription[] columnDescriptions = new ColumnDescription[]{
    new ColumnDescription("ref_edition", String.class),
    new ColumnDescription("Card Number", String.class),
    new ColumnDescription("Type", String.class),
    new ColumnDescription("Sub Type", String.class),
    new ColumnDescription("Mana Cost", String.class),
    new ColumnDescription("Converted Mana Cost", int.class),
    new ColumnDescription("power", int.class),
    new ColumnDescription("toughness", int.class),
    new ColumnDescription("imageURL", String.class),
    new ColumnDescription("Text", String.class),
    new ColumnDescription("Flavor", String.class),
    new ColumnDescription("Artist", String.class),
    new ColumnDescription("Rarity", String.class),
    new ColumnDescription("Name", String.class),
    new ColumnDescription("ID", String.class),

  };

  public MockTableModel(MagicCardRepository repository) {
    this.repository = repository;
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
    final CardDescription cardDescription = cards.get(rowIndex);
    return cardDescription.getCardId();
  }

  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
  }

  public void addTableModelListener(TableModelListener l) {
  }

  public void removeTableModelListener(TableModelListener l) {
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
