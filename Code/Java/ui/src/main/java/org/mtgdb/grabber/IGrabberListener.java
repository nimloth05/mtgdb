package org.mtgdb.grabber;

import org.mtgdb.model.Edition;
import org.mtgdb.model.MagicCard;

/**
 * @author Sandro Orlando
 */
public interface IGrabberListener {

  boolean beginEdition(final Edition edition);

  void grabbed(final MagicCard description);

  void endEdition();


}
