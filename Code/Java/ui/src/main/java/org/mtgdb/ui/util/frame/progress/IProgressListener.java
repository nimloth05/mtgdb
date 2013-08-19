package org.mtgdb.ui.util.frame.progress;

import java.util.List;

public interface IProgressListener {

  public void process(List<Object> chunks);

}
