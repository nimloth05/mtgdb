package org.mtgdb.ui.card;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.DefaultEventSelectionModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import com.google.inject.Inject;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.model.IMagicCard;
import org.mtgdb.model.MagicCard;
import org.mtgdb.ui.util.ImageLoader;
import org.mtgdb.ui.util.components.label.DefaultLabelModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

/**
 * @author Sandro Orlando
 */
public final class MagicCardPanelModel {

  private final IMagicCardRepository magicCardRepository;
  private final FilterList<MagicCard> filteredList;
  private DefaultLabelModel scanLabelModel = new DefaultLabelModel();
  private DefaultEventSelectionModel<MagicCard> tableSelectionModel;
  private final SortedList<MagicCard> sortedCards;
  private final AdvancedTableModel<MagicCard> magicCardTableModel;

  @Inject
  public MagicCardPanelModel(final IMagicCardRepository magicCardRepository) {
    this.magicCardRepository = magicCardRepository;

    EventList<MagicCard> cardList = new BasicEventList<>();
    sortedCards = new SortedList<>(cardList, new MagicCardComparator());

    filteredList = new FilterList<>(sortedCards);

    magicCardTableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(filteredList, new MagicCardTableFormat());

    tableSelectionModel = new DefaultEventSelectionModel<>(filteredList);
    tableSelectionModel.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(final ListSelectionEvent e) {


        final EventList<MagicCard> selected = tableSelectionModel.getSelected();
        if (selected.isEmpty()) return;
        IMagicCard selectedCard = selected.get(0);
        Icon scan = ImageLoader.loadAsIcon(selectedCard.getImageURL());
        scanLabelModel.setIcon(scan);
      }
    });
    tableSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    tableSelectionModel.setSelectionInterval(0, 0);
  }

  public MagicCardFilterator getFilterator() {
    return new MagicCardFilterator();
  }

  public FilterList<MagicCard> getFilteredCards() {
    return filteredList;
  }

  public DefaultLabelModel getScanLabelModel() {
    return scanLabelModel;
  }

  public ListSelectionModel getTableSelectionModel() {
    return tableSelectionModel;
  }

  public TableModel getLibraryModel() {
    return magicCardTableModel;
  }

  public void search(final String search) {
    sortedCards.clear();
    sortedCards.addAll(magicCardRepository.searchFreeText(search));
  }

  public void showAll() {
    sortedCards.clear();
    sortedCards.addAll(magicCardRepository.getAll());
  }

  public SortedList<MagicCard> getSortedList() {
    return sortedCards;
  }
}
