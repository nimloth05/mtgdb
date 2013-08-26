package org.mtgdb.util.assertion;


public class LocalizedIOException extends LocalizedException {

  public LocalizedIOException(final String message) {
    super(message);
  }
  
  public LocalizedIOException(final String message, final String localizedMessage) {
    super(message, localizedMessage);
  }

  public LocalizedIOException(final String uiMessage, final Throwable e) {
    super(uiMessage, e);
  }
  
  public LocalizedIOException(final Throwable t) {
    super(t);
  }

}
