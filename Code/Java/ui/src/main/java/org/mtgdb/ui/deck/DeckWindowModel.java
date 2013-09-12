package org.mtgdb.ui.deck;

import org.mtgdb.model.Deck;

/**
 * @author Sandro Orlando
 */
public final class DeckWindowModel {

  private DeckCardModel deckCardModel;
  private ChartModel chartModel;

  public DeckWindowModel(final Deck deck) {
    this.deckCardModel = DeckCardModel.create(deck);
    this.chartModel = new ChartModel(deck);
  }

  public DeckCardModel getDeckCardModel() {
    return deckCardModel;
  }

  public ChartModel getChartModel() {
    return chartModel;
  }
}
