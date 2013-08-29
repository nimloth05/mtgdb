package org.mtgdb.grabber;

/**
 * @author Sandro Orlando
 */
public interface IEditionGrabberListener {

  void grab(String url, String editionId);
}
