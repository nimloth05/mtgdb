package org.mtgdb.ui.main;

import com.google.inject.Inject;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.model.IMagicCard;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.main.action.ContainerPropertiesDialog;
import org.mtgdb.ui.main.action.GrabberAction;
import org.mtgdb.ui.main.action.ShowContainerAction;
import org.mtgdb.ui.search.SearchModel;
import org.mtgdb.ui.search.SearchWindow;
import org.mtgdb.ui.util.ImageLoader;
import org.mtgdb.ui.util.components.label.DefaultLabelModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;

/**
 * @author Sandro Orlando
 */
public final class MainModel {

  private final IDatabaseConnection connection;
  @Inject
  private GrabberAction grabberAction;
  @Inject
  private ContainerPropertiesDialog containerPropertiesDialog;
  private DefaultLabelModel scanLabelModel = new DefaultLabelModel();
  private DefaultListSelectionModel tableSelectionModel = new DefaultListSelectionModel();
  private MagicCardTableModel magicCardTableModel;
  @Inject
  private ShowContainerAction containerListAction;


  private Action showSearchAction = new ShowSearchAction();

  @Inject
  public MainModel(final IDatabaseConnection connection, final IMagicCardRepository magicCardRepository) {
    this.connection = connection;
    connection.openDB();
    magicCardTableModel = new MagicCardTableModel(magicCardRepository.getAll());
    tableSelectionModel.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(final ListSelectionEvent e) {
        if (magicCardTableModel.isEmpty()) return;
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

  public Action getShowContainersAction() {
    return containerListAction;
  }

  public Action getShowSearchAction() {
    return showSearchAction;
  }

  private class ShowSearchAction extends AbstractAction {

    public ShowSearchAction() {
      putValue(NAME, "Search Card");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      SearchWindow window = new SearchWindow(ServiceManager.get(SearchModel.class));
      window.show();
    }
  }
}
