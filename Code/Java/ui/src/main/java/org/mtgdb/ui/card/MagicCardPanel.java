package org.mtgdb.ui.card;

import net.miginfocom.swing.MigLayout;
import org.mtgdb.ui.util.components.label.LabelModelAdapter;
import org.mtgdb.util.Constants;

import javax.swing.*;
import javax.swing.text.PlainDocument;
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
    panel.add(new JTextField(new PlainDocument(), Constants.EMPTY, 50), "wrap");

    final JTable table = new JTable();
    table.setModel(model.getLibraryModel());
    table.setSelectionModel(model.getTableSelectionModel());
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
