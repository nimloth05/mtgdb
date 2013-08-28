package org.mtgdb.ui.card;

import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import net.miginfocom.swing.MigLayout;
import org.mtgdb.model.MagicCard;
import org.mtgdb.ui.util.components.label.LabelModelAdapter;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sandro Orlando
 */
public final class MagicCardPanel {

  private final MagicCardPanelModel model;
  private JPanel panel = new JPanel(new MigLayout(""));

  public MagicCardPanel(final MagicCardPanelModel model) {
    this.model = model;
    createContent();
  }

  private void createContent() {
    panel.add(new JLabel("Browser Cards"), "");
    panel.add(new JLabel("Filter:"), "split 2, align right");

    JTextField filterComponent = new JTextField(50);
    MatcherEditor<MagicCard> matcherEditor = new TextComponentMatcherEditor<>(filterComponent, model.getFilterator());
    model.getFilteredCards().setMatcherEditor(matcherEditor);

    panel.add(filterComponent, "wrap");

    final JTable table = new JTable();

    table.setModel(model.getLibraryModel());
    table.setSelectionModel(model.getTableSelectionModel());
        TableComparatorChooser.install(
          table, model.getSortedList(), TableComparatorChooser.MULTIPLE_COLUMN_MOUSE);

    final JScrollPane pane = new JScrollPane(table);
    panel.add(pane, "grow, push, spanx, split 2");

    final JLabel cardImageLabel = createImageLabel();
    cardImageLabel.setAlignmentY(JLabel.TOP);
    panel.add(cardImageLabel, "w 320!, aligny top, h 450!");
  }

  private JLabel createImageLabel() {
    final JLabel label = new JLabel();
    LabelModelAdapter.connect(label, model.getScanLabelModel());
    return label;
  }

  public Component getPanel() {
    return panel;
  }
}
