package org.mtgdb.ui.main.action;

import org.mtgdb.model.Deck;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.deck.DeckWindow;
import org.mtgdb.ui.deck.DeckWindowModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Sandro Orlando
 */
public final class ShowDeckWindow extends AbstractAction {

  public ShowDeckWindow() {
    putValue(NAME, "Show Deck Window");
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    DeckWindow window = new DeckWindow(new DeckWindowModel(ServiceManager.get(Deck.class)));
    window.show();
  }
}
