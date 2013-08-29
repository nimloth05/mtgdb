package org.mtgdb.ui.pensieve;

import com.google.inject.Inject;
import org.mtgdb.db.repository.ICardMemoryRepository;
import org.mtgdb.model.CardMemory;
import org.mtgdb.model.MagicCard;
import org.mtgdb.ui.card.MagicCardPanelModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class PensieveWindowModel {

  private final MagicCardPanelModel model;
  private final ICardMemoryRepository cardMemoryRepository;

  @Inject
  public PensieveWindowModel(final ICardMemoryRepository cardMemoryRepository) {
    this.cardMemoryRepository = cardMemoryRepository;
    model = MagicCardPanelModel.create(Collections.<Action>emptyList());

    loadData();
  }

  private void loadData() {
    final List<CardMemory> cardMemories = cardMemoryRepository.getAll();
    List<MagicCard> cards = new ArrayList<>(cardMemories.size());
    for (CardMemory cardMemory : cardMemories) {
      cards.add((MagicCard) cardMemory.getMagicCard());
    }
    model.display(cards);
  }

  public MagicCardPanelModel getMagicCardModel() {
    return model;
  }
}
