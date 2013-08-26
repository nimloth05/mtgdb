package org.mtgdb.ui.main;

import com.google.inject.Inject;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.model.IMagicCard;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.main.action.AddContainerAction;
import org.mtgdb.ui.main.action.GrabberAction;
import org.mtgdb.ui.util.ImageLoader;
import org.mtgdb.ui.util.components.label.DefaultLabelModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

/**
 * @author Sandro Orlando
 */
public final class MainModel {

  private final IDatabaseConnection connection;
  @Inject
  private GrabberAction grabberAction;
  @Inject
  private AddContainerAction addContainerAction;
  private DefaultLabelModel scanLabelModel = new DefaultLabelModel();
  private DefaultListSelectionModel tableSelectionModel = new DefaultListSelectionModel();
  private MagicCardTableModel magicCardTableModel;

  @Inject
  public MainModel(final IDatabaseConnection connection) {
    this.connection = connection;
    connection.openDB();
    magicCardTableModel = ServiceManager.get(MagicCardTableModel.class);
    tableSelectionModel.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(final ListSelectionEvent e) {
        IMagicCard selectedCard = magicCardTableModel.getCard(tableSelectionModel.getLeadSelectionIndex());
        Icon scan = ImageLoader.loadAsIcon(selectedCard.getImageURL());
        scanLabelModel.setIcon(scan);
      }
    });
    tableSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tableSelectionModel.setSelectionInterval(0, 0);
  }

  public DefaultLabelModel getScanLabelModel() {
    return scanLabelModel;
  }

  public ListSelectionModel getTableSelectionModel() {
    return tableSelectionModel;
  }

  public void closeDB() {
    connection.closeDB();
  }

  public Action getGrabberAction() {
    return grabberAction;
  }

  public TableModel getLibraryModel() {
    return magicCardTableModel;
  }

  public Action getAddContainerAction() {
    return addContainerAction;
  }

}
