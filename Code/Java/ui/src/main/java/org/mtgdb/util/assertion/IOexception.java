package org.mtgdb.util.assertion;


public class IOexception extends LocalizedException {

  public IOexception(final String message) {
    super(message);
  }
  
  public IOexception(final String message, final String localizedMessage) {
    super(message, localizedMessage);
  }

  public IOexception(final String uiMessage, final Throwable e) {
    super(uiMessage, e);
  }
  
  public IOexception(final Throwable t) {
    super(t);
  }

}
