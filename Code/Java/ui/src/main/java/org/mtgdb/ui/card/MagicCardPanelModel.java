package org.mtgdb.ui.card;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.DefaultEventSelectionModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import com.google.inject.Inject;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.model.MagicCard;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.search.ISelectionAwareAction;
import org.mtgdb.ui.util.ImageLoader;
import org.mtgdb.ui.util.components.label.DefaultLabelModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class MagicCardPanelModel {

  private static final String STATUS_LINE_MESSAGE = "%d Cards in list, displaying %d (%d filtered)";
  private final IMagicCardRepository magicCardRepository;
  private final FilterList<MagicCard> filteredList;
  private final SortedList<MagicCard> sortedCards;
  private final AdvancedTableModel<MagicCard> magicCardTableModel;
  private DefaultLabelModel scanLabelModel = new DefaultLabelModel();
  private DefaultEventSelectionModel<MagicCard> tableSelectionModel;
  private DefaultLabelModel statusLineModel = new DefaultLabelModel();
  private Collection<Action> actions = Collections.emptyList();

  @Inject
  private MagicCardPanelModel(final IMagicCardRepository magicCardRepository) {
    this.magicCardRepository = magicCardRepository;

    EventList<MagicCard> cardList = new BasicEventList<>();
    sortedCards = new SortedList<>(cardList, new MagicCardComparator());

    filteredList = new FilterList<>(sortedCards);
    statusLineModel = new DefaultLabelModel();
    filteredList.addListEventListener(new ListEventListener<MagicCard>() {
      @Override
      public void listChanged(final ListEvent<MagicCard> listChanges) {
        statusLineModel.setText(String.format(STATUS_LINE_MESSAGE, sortedCards.size(), filteredList.size(), sortedCards.size() - filteredList.size()));
      }
    });

    magicCardTableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(filteredList, new MagicCardTableFormat());

    tableSelectionModel = new DefaultEventSelectionModel<>(filteredList);
    tableSelectionModel.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(final ListSelectionEvent e) {

        final EventList<MagicCard> selected = tableSelectionModel.getSelected();
        if (selected.isEmpty()) return;
        MagicCard selectedCard = selected.get(0);
        Icon scan = ImageLoader.loadAsIcon(selectedCard.getImageURL());
        scanLabelModel.setIcon(scan);
        for (Action action : actions) {
          if (action instanceof ISelectionAwareAction) {
            ((ISelectionAwareAction) action).selected(selectedCard);
          }
        }
      }
    });
    tableSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }

  public static MagicCardPanelModel create(Collection<Action> actions) {
    MagicCardPanelModel model = ServiceManager.get(MagicCardPanelModel.class);
    model.init(actions);
    return model;
  }

  private void init(final Collection<Action> actions) {
    if (actions.isEmpty()) return;
    this.actions = actions;
  }

  public DefaultLabelModel getStatusLineModel() {
    return statusLineModel;
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

  public TableModel getTableModel() {
    return magicCardTableModel;
  }

  public void search(final String search) {
    display(magicCardRepository.searchFreeText(search));
  }

  public void showAll() {
    display(magicCardRepository.getAll());
  }

  public SortedList<MagicCard> getSortedList() {
    return sortedCards;
  }

  public void display(final List<MagicCard> cards) {
    sortedCards.clear();
    sortedCards.addAll(cards);
  }

  public Collection<Action> getActions() {
    return actions;
  }

  public void addCard(final MagicCard magicCard) {
    sortedCards.add(magicCard);
  }

  public void removeCard(final MagicCard card) {
    sortedCards.remove(card);
  }
}
