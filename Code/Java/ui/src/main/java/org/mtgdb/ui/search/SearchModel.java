package org.mtgdb.ui.search;

import com.google.inject.Inject;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.card.MagicCardPanelModel;
import org.mtgdb.ui.util.models.DocumentHelper;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Sandro Orlando
 */
public final class SearchModel {

  private Document freeTextSearch = new PlainDocument();
  private MagicCardPanelModel panelModel;
  private Action searchAction = new SearchAction();

  @Inject
  public SearchModel() {
    panelModel = MagicCardPanelModel.create(createContextActions());
    connectListener();
  }

  private Collection<Action> createContextActions() {
    Collection<Action> result = new LinkedList<>();
    result.add(ServiceManager.get(AddToPensieveAction.class));
    return result;
  }

  private void connectListener() {
  }

  public Document getFreeTextSearchModel() {
    return freeTextSearch;
  }

  public MagicCardPanelModel getMagicCardModel() {
    return panelModel;
  }

  public Action getSearchAction() {
    return searchAction;
  }

  private class SearchAction extends AbstractAction {

    public SearchAction() {
      putValue(NAME, "Search");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      panelModel.search(DocumentHelper.getText(freeTextSearch));
    }
  }
}
