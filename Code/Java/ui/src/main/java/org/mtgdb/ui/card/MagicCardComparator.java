package org.mtgdb.ui.card;

import org.mtgdb.model.MagicCard;

import java.util.Comparator;

/**
 * @author Sandro Orlando
 */
public final class MagicCardComparator implements Comparator<MagicCard>{

  @Override
  public int compare(final MagicCard o1, final MagicCard o2) {
    final int i = o1.getEdition().getId().compareTo(o2.getEdition().getId());
    if (i != 0) return i;

    int cardNumber = o1.getCardNumberWithoutSplitCard();
    int cardNumber2 = o2.getCardNumberWithoutSplitCard();
    return cardNumber - cardNumber2;
  }
}
