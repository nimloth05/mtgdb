package org.mtgdb.ui.search;

import com.google.inject.Inject;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.model.MagicCard;
import org.mtgdb.ui.main.MagicCardTableModel;
import org.mtgdb.ui.util.models.DocumentHelper;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class SearchModel {

  private final IMagicCardRepository magicCardRepository;
  private Document freeTextSearch = new PlainDocument();
  private MagicCardTableModel model;

  private Action searchAction = new SearchAction();

  @Inject
  public SearchModel(final IMagicCardRepository magicCardRepository) {
    this.magicCardRepository = magicCardRepository;
    model = new MagicCardTableModel(new LinkedList<MagicCard>());
    connectListener();
  }

  private void connectListener() {
  }

  public Document getFreeTextSearchModel() {
    return freeTextSearch;
  }

  public TableModel getMagicCardModel() {
    return model;
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
      final List<MagicCard> magicCards = magicCardRepository.searchFreeText(DocumentHelper.getText(freeTextSearch));
      model.updateData(magicCards);
    }
  }
}
