package org.mtgdb.ui.card;

import com.google.inject.Inject;
import org.mtgdb.db.repository.IMagicCardRepository;
import org.mtgdb.model.IMagicCard;
import org.mtgdb.model.MagicCard;
import org.mtgdb.ui.main.MagicCardTableModel;
import org.mtgdb.ui.util.ImageLoader;
import org.mtgdb.ui.util.components.label.DefaultLabelModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.util.LinkedList;

/**
 * @author Sandro Orlando
 */
public final class MagicCardPanelModel {

  private final IMagicCardRepository magicCardRepository;
  private DefaultLabelModel scanLabelModel = new DefaultLabelModel();
  private DefaultListSelectionModel tableSelectionModel = new DefaultListSelectionModel();
  private MagicCardTableModel magicCardTableModel;

  @Inject
  public MagicCardPanelModel(final IMagicCardRepository magicCardRepository) {
    this.magicCardRepository = magicCardRepository;
    magicCardTableModel = new MagicCardTableModel(new LinkedList<MagicCard>());
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

  public TableModel getLibraryModel() {
    return magicCardTableModel;
  }

  public void search(final String search) {
    magicCardTableModel.updateData(magicCardRepository.searchFreeText(search));
  }

  public void showAll() {
    magicCardTableModel.updateData(magicCardRepository.getAll());
  }
}
