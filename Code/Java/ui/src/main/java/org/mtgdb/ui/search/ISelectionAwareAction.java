package org.mtgdb.ui.search;

import org.mtgdb.model.MagicCard;

/**
 * @author Sandro Orlando
 */
public interface ISelectionAwareAction {

  public void selected(MagicCard magicCard);

}
