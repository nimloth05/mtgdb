package org.mtgdb.ui.util.frame.progress;

public interface IProgressRunnable {

  public void done();

  public void run(IProgressMonitor monitor) throws Exception;

}
