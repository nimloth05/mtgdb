package org.mtgdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A user-created magic deck
 * @author Sandro Orlando
 * @author Michael Sacher
 */
public final class Deck {

  @Id
  @GeneratedValue
  private int id;
  @Column
  private String name;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }
 
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

  public Collection<MagicCard> getCards() {
    return cards;
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







