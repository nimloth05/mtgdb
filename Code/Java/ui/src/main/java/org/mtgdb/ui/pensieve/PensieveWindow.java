package org.mtgdb.ui.pensieve;

import net.miginfocom.swing.MigLayout;
import org.mtgdb.ui.card.MagicCardPanel;
import org.mtgdb.ui.util.frame.FrameFactory;

import javax.swing.*;

/**
 * @author Sandro Orlando
 */
public final class PensieveWindow {

  private final PensieveWindowModel model;
  private JDialog frame;

  public PensieveWindow(final PensieveWindowModel model) {
    this.model = model;
    createContent();
  }

  private void createContent() {
    frame = FrameFactory.createCenteredDialog(FrameFactory.getMainFrame(), "Card Pensieve", 1000, 800);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(new MigLayout());

    frame.getContentPane().add(new MagicCardPanel(model.getMagicCardModel()).getPanel(), "push, grow");
  }

  public void show() {
    frame.setVisible(true);
  }
}
