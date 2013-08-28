package org.mtgdb.ui.search;

import net.miginfocom.swing.MigLayout;
import org.mtgdb.ui.util.frame.FrameFactory;
import org.mtgdb.util.Constants;

import javax.swing.*;

/**
 * @author Sandro Orlando
 */
public final class SearchWindow {

  private final SearchModel model;
  private JFrame frame;

  public SearchWindow(final SearchModel model) {
    this.model = model;
    createContent();
  }

  private void createContent() {
    frame = FrameFactory.createCenteredFrame("Search card", 800, 600);
    frame.getContentPane().setLayout(new MigLayout());

    JTextField freeTextSearch = new JTextField(model.getFreeTextSearchModel(), Constants.EMPTY, 200);
    frame.getContentPane().add(freeTextSearch);

    frame.getContentPane().add(new JButton(model.getSearchAction()), "wrap");

    JTable table = new JTable(model.getMagicCardModel());
    frame.getContentPane().add(new JScrollPane(table), "push, grow");
  }

  public void show() {
    frame.setVisible(true);
  }

}
