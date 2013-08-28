package org.mtgdb.ui.card;

import ca.odell.glazedlists.TextFilterator;
import org.mtgdb.model.MagicCard;

import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class MagicCardFilterator implements TextFilterator<MagicCard>{

  @Override
  public void getFilterStrings(final List<String> strings, final MagicCard magicCard) {
    strings.add(magicCard.getArtist());
    strings.add(Integer.toString(magicCard.getConvertedManaCost()));
    strings.add(magicCard.getFlavorText());
    strings.add(magicCard.getId());
    strings.add(magicCard.getName());
    strings.add(magicCard.getManaCost());
    strings.add(magicCard.getSubType());
    strings.add(magicCard.getText());
    strings.add(magicCard.getType());
    strings.add(magicCard.getEdition().getId());
    strings.add(magicCard.getEdition().getName());
    strings.add(magicCard.getRarity().name());
  }
}
