package ch.rabbithole.util.assertion;



public final class Assert {
  
  public static IAssertionHandler sAssertionHandler = new DefaultAssertionHandler();
  
  private static final String PARAM_NULL = "Parameter is null"; //$NON-NLS-1$ 
  
  public static void log(final String message) {
    sAssertionHandler.log(message);
  }
  
  public static void log(final Throwable t) {
    sAssertionHandler.log(t);
  }

  public static void log(Throwable t, String message) {
    sAssertionHandler.log(t, message);
  }
  
  public static void notSupported() {
    throw new UnsupportedOperationException("Operation not suppoerted on this object!"); //$NON-NLS-1$
  }
  
  public static IAssertionHandler setAssertionHandler(final IAssertionHandler handler) {
    final IAssertionHandler oldAssertionHandler = sAssertionHandler;
    sAssertionHandler = handler;
    return oldAssertionHandler;
  }

  public static void unreachableCode() {
    throw new IllegalStateException("Unreachable Code... voellig unbruchbar! (-:"); //$NON-NLS-1$
  }

  public static void whereAmI(final String message) {
    new RuntimeException(message).printStackTrace();
  }

}
