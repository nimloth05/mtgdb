package org.mtgdb.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A user-created magic deck
 *
 * @author Sandro Orlando
 * @author Michael Sacher
 */
public final class Deck {

  @Id
  @GeneratedValue
  private int id;
  @Column
  private String name;
  private Collection<MagicCard> cards;
  private DeckStats stats;

  public Deck() {
    cards = new ArrayList<>();
    stats = new DeckStats();
  }

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

  public Integer[] calcManaCurve() {
    Predicate predicateManaCost1 = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getConvertedManaCost() == 1;
      }
    };
    Predicate predicateManaCost2 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 2;
        }
      };
    Predicate predicateManaCost3 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 3;
        }
      };
    Predicate predicateManaCost4 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 4;
        }
      };
    Predicate predicateManaCost5 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 5;
        }
      };
    Predicate predicateManaCost6 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 6;
        }
      };
    Predicate predicateManaCost7 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 7;
        }
      };
    Integer[] manaCurve = {0,0,0,0,0,0,0,0,0,0,0};

    Collection filtered;
    filtered = CollectionUtils.select(cards, predicateManaCost1);
    manaCurve[1] = filtered.size();
    filtered = CollectionUtils.select(cards, predicateManaCost2);
    manaCurve[2] = filtered.size();
    filtered = CollectionUtils.select(cards, predicateManaCost3);
    manaCurve[3] = filtered.size();
    filtered = CollectionUtils.select(cards, predicateManaCost4);
    manaCurve[4] = filtered.size();
    filtered = CollectionUtils.select(cards, predicateManaCost5);
    manaCurve[5] = filtered.size();
    filtered = CollectionUtils.select(cards, predicateManaCost6);
    manaCurve[6] = filtered.size();
    filtered = CollectionUtils.select(cards, predicateManaCost7);
    manaCurve[7] = filtered.size();
    return manaCurve;
  }

  public Map<String, Integer> calcDeckComponents() {
    Map<String, Integer> components = new HashMap<>();

    Predicate predicateCreature = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getType().contains("Creature");
      }
    };

    Predicate predicateInstant = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getType().contains("Instant");
      }
    };

    Predicate predicateSorcery = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getType().contains("Sorcery");
      }
    };

    Predicate predicateLand = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getType().contains("Land");
      }
    };
    Collection filtered;

    filtered = CollectionUtils.select(cards, predicateCreature);
    components.put("Creatures", filtered.size());

    filtered = CollectionUtils.select(cards, predicateInstant);
    components.put("Instants", filtered.size());

    filtered = CollectionUtils.select(cards, predicateSorcery);
    components.put("Sorceries", filtered.size());

    filtered = CollectionUtils.select(cards, predicateLand);
    components.put("Lands", filtered.size());

    return components;
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
      String deckSelectSQL = "SELECT * FROM \"Deck\" INNER JOIN \"CardDescriptionXDeck\" INNER JOIN \"CardDescription\" WHERE \"Deck.Name\"=\"" + name + "\";";
      String numCreaturesSQL = "SELECT count(*) FROM (" + deckSelectSQL + ") where \"type\" LIKE %Creature% ";
    }
  }
}







