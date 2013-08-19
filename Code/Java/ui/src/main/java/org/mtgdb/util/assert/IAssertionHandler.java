package ch.rabbithole.util.assertion;

public interface IAssertionHandler {
  
  /**
   * Loggt die Message. 
   * @param message
   */
  public void log(String message);
  
  /**
   * Loggt die Exception.
   * @param t
   */
  public void log(Throwable t);

  /**
   * Loggt die Nachricht zusammen mit der Exception.
   * @param t
   * @param message
   */
  public void log(Throwable t, String message);
  
}
