package org.mtgdb.ui.util.frame.progress;

public interface IProgressMonitor {
  
  public void setMessage(String message);
  
  public void step(int step);
 

}
