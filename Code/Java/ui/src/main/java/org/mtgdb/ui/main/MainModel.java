package org.mtgdb.ui.main;

import com.google.inject.Inject;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.card.MagicCardPanelModel;
import org.mtgdb.ui.main.action.AddPhysicalCardAction;
import org.mtgdb.ui.main.action.ContainerPropertiesDialog;
import org.mtgdb.ui.main.action.GrabberAction;
import org.mtgdb.ui.main.action.OpenPensieveDialog;
import org.mtgdb.ui.main.action.ShowContainerAction;
import org.mtgdb.ui.main.action.ShowSearchAction;

import javax.swing.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class MainModel {

  private final IDatabaseConnection connection;
  @Inject
  private ContainerPropertiesDialog containerPropertiesDialog;
  @Inject
  private MagicCardPanelModel panelModel;

  private Collection<Action> toolbarActions;

  @Inject
  public MainModel(final IDatabaseConnection connection) {
    this.connection = connection;
    connection.openDB();
    toolbarActions = createToolbarActions();
  }

  private Collection<Action> createToolbarActions() {
    List<Action> actions = new LinkedList<>();
    actions.add(ServiceManager.get(GrabberAction.class));
    actions.add(ServiceManager.get(ShowContainerAction.class));
    actions.add(ServiceManager.get(ShowSearchAction.class));
    actions.add(ServiceManager.get(OpenPensieveDialog.class));
    actions.add(ServiceManager.get(AddPhysicalCardAction.class));
    return actions;
  }

  public MagicCardPanelModel getPanelModel() {
    return panelModel;
  }

  public void closeDB() {
    connection.closeDB();
  }

  public Collection<Action> getToolbarActions() {
    return toolbarActions;
  }

}
