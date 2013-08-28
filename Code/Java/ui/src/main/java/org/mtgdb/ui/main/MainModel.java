package org.mtgdb.ui.main;

import com.google.inject.Inject;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.card.MagicCardPanelModel;
import org.mtgdb.ui.main.action.ContainerPropertiesDialog;
import org.mtgdb.ui.main.action.GrabberAction;
import org.mtgdb.ui.main.action.ShowContainerAction;
import org.mtgdb.ui.search.SearchModel;
import org.mtgdb.ui.search.SearchWindow;

import javax.swing.*;
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
  @Inject
  private ShowContainerAction containerListAction;
  private Action showSearchAction = new ShowSearchAction();
  @Inject
  private MagicCardPanelModel panelModel;

  @Inject
  public MainModel(final IDatabaseConnection connection, final IMagicCardRepository magicCardRepository) {
    this.connection = connection;
    connection.openDB();
  }

  public MagicCardPanelModel getPanelModel() {
    return panelModel;
  }

  public void closeDB() {
    connection.closeDB();
  }

  public Action getGrabberAction() {
    return grabberAction;
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
