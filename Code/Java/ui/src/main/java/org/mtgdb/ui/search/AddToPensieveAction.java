package org.mtgdb.ui.search;

import com.google.inject.Inject;
import org.mtgdb.db.repository.ICardMemoryRepository;
import org.mtgdb.model.MagicCard;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Sandro Orlando
 */
public final class AddToPensieveAction extends AbstractAction implements ISelectionAwareAction{

  private final ICardMemoryRepository repository;
  private MagicCard currentCard;

  @Inject
  public AddToPensieveAction(final ICardMemoryRepository repository) {
    this.repository = repository;
    putValue(NAME, "Remember");
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    if (currentCard == null) return;
    repository.rememberCard(currentCard);
  }

  @Override
  public void selected(final MagicCard magicCard) {
    currentCard = magicCard;
  }
}
