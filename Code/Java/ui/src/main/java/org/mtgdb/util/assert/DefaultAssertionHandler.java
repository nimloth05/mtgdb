package ch.rabbithole.util.assertion;

public class DefaultAssertionHandler implements IAssertionHandler {

  public void fail(final Throwable t) {
    throw new RuntimeException(t);
  }

  public void log(final String message) {
    System.out.println(message);
  }

  public void log(final Throwable t) {
    t.printStackTrace();
  }

  @Override
  public void log(Throwable t, String message) {
    System.err.println(message);
    t.printStackTrace();
  }

}
