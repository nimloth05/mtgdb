package org.mtgdb.ui;

import org.mtgdb.model.MagicCard;
import org.mtgdb.db.DatabaseAccess;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * @author Michael Sacher
 */
public class MockTableModel implements TableModel {

  private String[] headers = {"ref_edition",
    "Card Number",
    "Type",
    "Sub Type",
    "Mana Cost",
  "Converted Mana Cost",
  "power",
  "toughness",
  "imageURL",
  "Text",
  "Flavor",
  "Artist",
  "Rarity",
  "Name",
  "ID"};
  private String[][] data = {{"stuff1","stuff2"},{"things1","things2"}};
  private DatabaseAccess dbAccess;
  private final ArrayList<MagicCard> cards;

  public MockTableModel(DatabaseAccess dbAccess) {
    this.dbAccess = dbAccess;
    cards = dbAccess.getAllCards();
  }

  public int getRowCount() {
    return cards.size();
  }

  public int getColumnCount() {
    return cards.get(0).toArray().length;
  }

  public String getColumnName(int columnIndex) {
    return headers[columnIndex];
  }

  public Class<?> getColumnClass(int columnIndex) {
    return cards.get(0).toArray()[columnIndex].getClass();
  }

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    return cards.get(rowIndex).toArray()[columnIndex];
  }

  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void addTableModelListener(TableModelListener l) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void removeTableModelListener(TableModelListener l) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
