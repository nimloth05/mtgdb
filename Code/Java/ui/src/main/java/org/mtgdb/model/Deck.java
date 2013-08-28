package org.mtgdb.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A user-created magic deck consists of Library Cards
 *
 * @author Michael Sacher
 */
//TODO: Maybe add possibility to add not-owned card
public class Deck {
  private String name;
  private String description;
  private Collection<MagicCard> cards;
  private DeckStats stats;

  public void addCard(MagicCard card) {
    cards.add(card);
  }

  public void removeCard(MagicCard card) {
    cards.remove(card);
  }

  public void addCards(Collection<MagicCard> cardsToAdd) {
    cards.addAll(cardsToAdd);
  }

  public void clear() {
    cards.clear();
  }

  private class DeckStats {
    Map<String, Integer> statistics = new HashMap<String, Integer>();

    public DeckStats() {
      statistics.put("numCreatures", 0);
      statistics.put("numSorceries", 0);
      statistics.put("numInstants", 0);
      statistics.put("numArtifacts", 0);
      statistics.put("numLands", 0);
      statistics.put("0manaSpells", 0);
      statistics.put("1manaSpells", 0);
      statistics.put("2manaSpells", 0);
      statistics.put("3manaSpells", 0);
      statistics.put("4manaSpells", 0);
      statistics.put("5manaSpells", 0);
      statistics.put("6manaSpells", 0);
      statistics.put("7manaSpells", 0);
      statistics.put("8manaSpells", 0);
      statistics.put("9manaSpells", 0);
      statistics.put("10manaSpells", 0);
    }

    private void update() {
       String deckSelectSQL = "SELECT * FROM \"Deck\" INNER JOIN \"CardDescriptionXDeck\" INNER JOIN \"CardDescription\" WHERE \"Deck.Name\"=\""+name+"\";";
       String numCreaturesSQL = "SELECT count(*) FROM ("+deckSelectSQL+") where \"type\" LIKE %Creature% ";
    }
  }
}









