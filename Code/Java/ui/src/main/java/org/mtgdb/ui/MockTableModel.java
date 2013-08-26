package org.mtgdb.ui;

import com.google.inject.Inject;
import org.mtgdb.db.repository.MagicCardRepository;
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
    switch (columnIndex) {
      case 0: return cardDescription.getEdition();
      case 1: return cardDescription.getName();
      case 2: return cardDescription.getType();
      case 3: return cardDescription.getSubType();
      case 4: return cardDescription.getRarity();
      case 5: return cardDescription.getManaCost();
      case 6: return cardDescription.getConvManaCost();
      case 7: return cardDescription.getPower();
      case 8: return cardDescription.getToughness();
      case 9: return cardDescription.getCardText();
      case 10: return cardDescription.getFlavorText();
      case 11: return cardDescription.getArtist();
      case 12: return cardDescription.getNumber();
    }
    return cardDescription.getCardId();
  }

  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
  }

  public void addTableModelListener(TableModelListener l) {
  }

  public void removeTableModelListener(TableModelListener l) {
  }

  public CardDescription getCard(final int index) {
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
