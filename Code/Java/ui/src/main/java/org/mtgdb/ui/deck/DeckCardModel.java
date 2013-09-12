package org.mtgdb.ui.deck;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import org.mtgdb.model.Deck;
import org.mtgdb.model.MagicCard;
import org.mtgdb.model.events.CardAddedToDeckEvent;
import org.mtgdb.model.events.CardRemovedFroMDeck;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.card.MagicCardPanelModel;
import org.mtgdb.ui.search.ISelectionAwareAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Sandro Orlando
 */
public final class DeckCardModel {

  private final EventBus eventBus;
  private Deck deck;
  private MagicCardPanelModel deckCardsModel;
  private MagicCardPanelModel cardDatabaseModel;

  @Inject
  public DeckCardModel(final MagicCardPanelModel deckCardModel, final EventBus eventBus) {
    this.deckCardsModel = deckCardModel;
    this.cardDatabaseModel = MagicCardPanelModel.create(createCardDatabaseActions());
    this.eventBus = eventBus;
    this.cardDatabaseModel.showAll();

  }

  public static DeckCardModel create(final Deck deck) {
    DeckCardModel model = ServiceManager.get(DeckCardModel.class);
    model.init(deck);
    return model;
  }

  public MagicCardPanelModel getDeckCardsModel() {
    return deckCardsModel;
  }

  public MagicCardPanelModel getCardDatabaseModel() {
    return cardDatabaseModel;
  }

  private void init(final Deck deck) {
    this.deck = deck;
    eventBus.register(new Listener());
  }

  private Collection<Action> createCardDatabaseActions() {
    return Arrays.<Action>asList(
      new AddCarAction()
    );
  }

  private class Listener {

    public Listener() {
    }

    @Subscribe
    public void cardAdded(final CardAddedToDeckEvent event) {
      if (event.getDeck().getId() != deck.getId()) return;
      deckCardsModel.addCard(event.getMagicCard());
    }

    @Subscribe
    public void cardRemoved(final CardRemovedFroMDeck event) {
      if (event.getDeck().getId() != deck.getId()) return;
      deckCardsModel.removeCard(event.getCard());
    }

  }

  private class AddCarAction extends AbstractAction implements ISelectionAwareAction{

    private MagicCard selectedCard;

    private AddCarAction() {
      putValue(NAME, "Add Card to Deck");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      if (selectedCard == null) return;
      deck.addCard(selectedCard);
    }

    @Override
    public void selected(final MagicCard magicCard) {
      this.selectedCard = magicCard;
    }
  }
}
