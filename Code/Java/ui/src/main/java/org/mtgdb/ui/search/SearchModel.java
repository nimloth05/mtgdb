package org.mtgdb.ui.search;

import com.google.inject.Inject;
import org.mtgdb.ui.card.MagicCardPanelModel;
import org.mtgdb.ui.util.models.DocumentHelper;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;

/**
 * @author Sandro Orlando
 */
public final class SearchModel {

  private Document freeTextSearch = new PlainDocument();
  private MagicCardPanelModel panelModel;
  private Action searchAction = new SearchAction();

  @Inject
  public SearchModel(MagicCardPanelModel magicCardPanelModel) {
    panelModel = magicCardPanelModel;
    connectListener();
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
