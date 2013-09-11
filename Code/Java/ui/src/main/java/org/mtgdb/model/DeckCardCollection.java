package org.mtgdb.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;
import com.sun.istack.internal.Nullable;
import org.mtgdb.db.repository.IDeckMagicCardRepository;
import org.mtgdb.services.ServiceManager;

import java.util.Collection;

/**
 * @author Sandro Orlando
 */
public final class DeckCardCollection {

  private final IDeckMagicCardRepository repository;

  public static DeckCardCollection create() {
    return ServiceManager.get(DeckCardCollection.class);
  }

  @Inject
  public DeckCardCollection(final IDeckMagicCardRepository repository) {
    this.repository = repository;
  }

  public void add(final Deck deck, final MagicCard card) {
    repository.save(new DeckMagicCard(deck, card));
  }

  public void remove(final Deck deck, final MagicCard card) {
    repository.delete(deck.getId(), card.getId());
  }

  public void addAll(final Deck deck, final Collection<MagicCard> cards) {
    for (MagicCard magicCard : cards) {
      add(deck, magicCard);
    }
  }

  public void clear(final Deck deck) {
    throw new UnsupportedOperationException("TBI");
  }

  public Collection<MagicCard> toCollection(final Deck deck) {
    return Collections2.transform(repository.getForDeck(deck), new Function<DeckMagicCard, MagicCard>() {
      @Override
      public MagicCard apply(@Nullable final DeckMagicCard deckMagicCard) {
        return deckMagicCard.getMagicCard();
      }
    });
  }

}
