package org.mtgdb.model;

import com.j256.ormlite.field.DatabaseField;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ORMLite requires an additional DAO for many to many relations. This DAO represents the manyToMany relation between MagicCard and Deck.
 *
 * @author Sandro Orlando
 */
public final class DeckMagicCard {

  public static final String DECK_ID_FIELD_NAME = "REF_DECK";
  public static final String MAGIC_CARD_ID_FIELD_NAME = "REF_MAGIC_CARD";
  @Id
  @GeneratedValue
  private int id;
  @DatabaseField(foreign = true, columnName = DECK_ID_FIELD_NAME)
  private Deck deck;
  @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = MAGIC_CARD_ID_FIELD_NAME)
  private MagicCard magicCard;

  public DeckMagicCard() {
  }

  public DeckMagicCard(final Deck deck, final MagicCard magicCard) {
    this.deck = deck;
    this.magicCard = magicCard;
  }

  public Deck getDeck() {
    return deck;
  }

  public void setDeck(final Deck deck) {
    this.deck = deck;
  }

  public MagicCard getMagicCard() {
    return magicCard;
  }

  public void setMagicCard(final MagicCard magicCard) {
    this.magicCard = magicCard;
  }
}

