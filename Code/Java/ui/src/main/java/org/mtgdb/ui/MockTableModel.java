package org.mtgdb.ui;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * @author Michael Sacher
 */
public class MockTableModel implements TableModel {

  private String[] headers = {"col1","col2"};
  private String[][] data = {{"stuff1","stuff2"},{"things1","things2"}};

  public int getRowCount() {
    return 2;
  }

  public int getColumnCount() {
    return 2;
  }

  public String getColumnName(int columnIndex) {
    return headers[columnIndex];
  }

  public Class<?> getColumnClass(int columnIndex) {
    return String.class;
  }

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    return data[rowIndex][columnIndex];
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
