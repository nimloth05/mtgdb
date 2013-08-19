package org.mtgdb.ui.util.frame.progress;

import javax.swing.*;
import java.util.List;

public class SwingWorkerProgressMonitor extends SwingWorker<Void, Object> implements IProgressMonitor {
  
  private final IProgressRunnable runnable;
  private final IProgressListener listener;

  public SwingWorkerProgressMonitor(IProgressRunnable runnable, IProgressListener listener) {
    this.runnable = runnable;
    this.listener = listener;
  }
  
  @Override
  public void setMessage(String message) {
    publish(message);
  }

  @Override
  public void step(int step) {
    publish(step);
  }

  @Override
  protected Void doInBackground() throws Exception {
    runnable.run(this);
    return null;
  }

  @Override
  protected void done() {
    runnable.done();
  }

  @Override
  protected void process(List<Object> chunks) {
    listener.process(chunks);
  }
  
}
