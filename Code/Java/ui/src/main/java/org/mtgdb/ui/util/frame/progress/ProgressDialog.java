package org.mtgdb.ui.util.frame.progress;

import org.mtgdb.ui.util.frame.FrameFactory;
import org.mtgdb.ui.util.frame.FrameUtil;
import org.mtgdb.util.assertion.Assert;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Erstellt einen Dialog mit Progressbar als einzige GUI Komponente.
 *
 * @author josiane
 */
public final class ProgressDialog {

  private static final int WIDTH  = 300;
  private static final int HEIGHT = 100;
  private JDialog dialog;
  private JProgressBar progressBar;

  private final SwingWorkerProgressMonitor swingWorker;

  public static ProgressDialog create(IProgressRunnable runnable) {
    return create(FrameFactory.getMainFrame(), runnable);
  }

  public static ProgressDialog create(Window parent, IProgressRunnable runnable) {
    return new ProgressDialog(parent, runnable);
  }

  private ProgressDialog(Window parent, IProgressRunnable runnable) {
    swingWorker = new SwingWorkerProgressMonitor(adaptRunnable(runnable), createListener());
    initComponents(parent);
  }

  public void setMax(int max) {
    progressBar.setMaximum(max);
  }

  public void setMin(int min) {
    progressBar.setIndeterminate(false);
    progressBar.setMinimum(min);
  }

  /**
   * Führt jede String-Nachricht die übergeben wird, wird der progress-Count erhöt.
   *
   * @param message
   */
  public void setProgressBarLabel(String message) {
    progressBar.setString(message);
  }

  public void setTitle(String title) {
    dialog.setTitle(title);
  }

  public void start() {
    swingWorker.execute();
    dialog.setVisible(true);
  }

  private IProgressRunnable adaptRunnable(final IProgressRunnable runnable) {
    return new IProgressRunnable() {

      @Override
      public void done() {
        ProgressDialog.this.done();
        runnable.done();
      }

      @Override
      public void run(IProgressMonitor monitor) throws Exception {
        try {
          runnable.run(monitor);
        }
        catch (Exception e) {
          Assert.log(e);
          throw new RuntimeException(e);
        }
      }
    };
  }

  private IProgressListener createListener() {
    return new IProgressListener() {

      @Override
      public void process(List<Object> chunks) {
        for(Object object: chunks) {
          if (object instanceof Integer) {
            Integer value = (Integer) object;
            progressBar.setValue(value.intValue());
          }

          if (object instanceof String) {
            String message = (String) object;
            setProgressBarLabel(message);

          }
        }
      }
    };
  }

  private void done() {
    FrameUtil.close(dialog);
  }

  private void initComponents(Window parent) {
    dialog = FrameFactory.createCenteredDialog(parent, WIDTH, HEIGHT);

    progressBar = new JProgressBar();
    progressBar.setIndeterminate(true);
    progressBar.setStringPainted(true);

    dialog.getContentPane().add(progressBar);
  }
  
}
