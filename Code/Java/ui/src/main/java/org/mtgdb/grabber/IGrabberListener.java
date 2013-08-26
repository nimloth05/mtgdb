package org.mtgdb.grabber;

import org.mtgdb.model.MagicCard;
import org.mtgdb.model.Edition;

/**
 * @author Sandro Orlando
 */
public interface IGrabberListener {

  void beginEdition(final Edition edition);

  void grabbed(final MagicCard description);

  void endEdition();



}
