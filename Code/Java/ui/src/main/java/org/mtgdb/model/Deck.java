package org.mtgdb.model;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.sun.istack.internal.Nullable;
import org.mtgdb.model.events.CardAddedToDeckEvent;
import org.mtgdb.model.events.CardRemovedFroMDeck;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

  private EventBus bus;
  @Id
  @GeneratedValue
  private int id;
  @Column
  private String name;

  private DeckCardCollection cards;

  public Deck() {}

  @Inject
  public Deck(final DeckCardCollection collection, final EventBus bus) {
    this.bus = bus;
    this.cards = collection;
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
    cards.add(this, card);
    bus.post(new CardAddedToDeckEvent(card, this));
  }

  public void removeCard(MagicCard card) {
    cards.remove(this, card);
    bus.post(new CardRemovedFroMDeck(card, this));
  }

  public void addCards(Collection<MagicCard> cardsToAdd) {
    cards.addAll(this, cardsToAdd);
  }

  public void clear() {
    cards.clear(this);
  }

  public Collection<MagicCard> getCards() {
    return cards.toCollection(this);
  }

  public int[] calcManaCurve() {
    final int curveCount = 7;
    int[] manaCurve = new int[curveCount];
    for (int i = 0; i < curveCount; i++) {
      Predicate<MagicCard> predicate = createManaCurvePredicate(i + 1);
      manaCurve[i] = Collections2.filter(getCards(), predicate).size();
    }
    return manaCurve;
  }

  private Predicate<MagicCard> createManaCurvePredicate(final int convertedManaCost) {
    return new Predicate<MagicCard>() {
      @Override
      public boolean apply(@Nullable final MagicCard input) {
        return input.getConvertedManaCost() == convertedManaCost;
      }
    };
  }

  private Predicate<MagicCard> createCardTypePredicate(final String type) {
    return new Predicate<MagicCard>() {

      @Override
      public boolean apply(@Nullable final MagicCard input) {
        return input.getType().contains(type);
      }
    };
  }

  public Map<String, Integer> calcDeckComponents() {
    Map<String, Integer> components = new HashMap<>();
    final String[] types = {"Creature", "Instant", "Sorcery", "Land"};
    for (String type : types) {
      final Predicate<MagicCard> predicate = createCardTypePredicate(type);
      components.put(type, Collections2.filter(getCards(), predicate).size());
    }
    return components;
  }

}







