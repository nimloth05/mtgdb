package org.mtgdb.grabber;

import org.mtgdb.model.CardDescription;

/**
 * @author Sandro Orlando
 */
public interface IGrabberListener {

  void grabbed(final CardDescription description);



}
