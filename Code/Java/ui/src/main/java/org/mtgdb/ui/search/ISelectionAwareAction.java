package org.mtgdb.ui.search;

import org.mtgdb.model.IMagicCard;

/**
 * @author Sandro Orlando
 */
public interface ISelectionAwareAction {

  public void selected(IMagicCard magicCard);

}
