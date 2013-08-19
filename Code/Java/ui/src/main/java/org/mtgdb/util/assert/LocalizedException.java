package ch.rabbithole.util.assertion;

/**
 * @author Sandro
 */
public class LocalizedException extends RuntimeException {

  private String fLocalizedMessage;

  public LocalizedException(final String message) {
    super(message);
  }

  public LocalizedException(final String message, final String localizedMessage) {
    super(message);
    fLocalizedMessage = localizedMessage;
  }

  public LocalizedException(final String uiMessage, final Throwable e) {
    super(uiMessage, e);
  }

  public LocalizedException(final Throwable t) {
    super(t);
  }

  @Override
  public String getLocalizedMessage() {
    return fLocalizedMessage != null ? fLocalizedMessage : super.getLocalizedMessage();
  }
}
