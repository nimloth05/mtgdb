package org.mtgdb.ui.main.action;

import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.search.SearchModel;
import org.mtgdb.ui.search.SearchWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
* @author Sandro Orlando
*/
public class ShowSearchAction extends AbstractAction {

  public ShowSearchAction() {
    putValue(NAME, "Search Card");
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    SearchWindow window = new SearchWindow(ServiceManager.get(SearchModel.class));
    window.show();
  }
}
