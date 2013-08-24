package org.mtgdb.ui;

import org.jdesktop.swingx.VerticalLayout;
import org.mtgdb.db.DatabaseAccess;
import org.mtgdb.db.DatabaseConnection;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.grabber.GrabberJsoup;
import org.mtgdb.grabber.IGrabberListener;
import org.mtgdb.model.CardDescription;
import org.mtgdb.model.Container;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;
import org.mtgdb.ui.util.dialog.properties.PropertyFactory;
import org.mtgdb.ui.util.dialog.properties.PropertyGroup;
import org.mtgdb.ui.util.dialog.properties.string.IStringModelBody;
import org.mtgdb.ui.util.dialog.ui.PropertiesPane;
import org.mtgdb.ui.util.frame.FrameFactory;
import org.mtgdb.ui.util.frame.FrameUtil;
import org.mtgdb.ui.util.frame.progress.IProgressMonitor;
import org.mtgdb.ui.util.frame.progress.IProgressRunnable;
import org.mtgdb.ui.util.frame.progress.ProgressDialog;
import org.mtgdb.ui.util.models.DocumentHelper;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class MainModel {

  private final DatabaseAccess dbAccess;
  private Action grabberAction = new GrabberAction();
  private Action addContainerAction = new AddContainerAction();

  public MainModel() {
    IDatabaseConnection connection = DatabaseConnection.create();
    dbAccess = new DatabaseAccess(connection);
  }

  public void closeDB() {
    dbAccess.closeDB();
  }

  public Action getGrabberAction() {
    return grabberAction;
  }

  public TableModel getLibraryModel() {
    return new MockTableModel(dbAccess);
  }

  public Action getAddContainerAction() {
    return addContainerAction;
  }

  private class AddContainerAction extends AbstractAction {

    private Container container = new Container();

    public AddContainerAction() {
      super();
      putValue(Action.NAME, "Add Container");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      PropertyGroup group = new PropertyGroup("group1");
      PropertyFactory factory = new PropertyFactory();
      group.add(factory.stringProperty("name", createNameBody()).setLabel("Name"));
      group.add(factory.stringProperty("description", createDescriptionBody()).setLabel("Description"));

      final PropertiesPane pane = new PropertiesPane(group);
      final JDialog dialog = FrameFactory.createCenteredDialog(FrameFactory.getMainFrame(), "Container Properties", 200, 200);

      dialog.getContentPane().setLayout(new VerticalLayout());
      dialog.getContentPane().add(pane.getComponent());

      JButton okButton = new JButton("Ok");
      okButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(final ActionEvent e) {
          pane.ok();
          dbAccess.saveContainer(container);
          FrameUtil.close(dialog);
        }
      });
      dialog.getContentPane().add(okButton);

      dialog.setVisible(true);
    }

    private IStringModelBody createDescriptionBody() {
      return new IStringModelBody() {
        @Override
        public Document initialize(final IPropertyModelContext context) {
          return new PlainDocument();
        }

        @Override
        public void ok(final Document document) {
          container.setDescription(DocumentHelper.getText(document));
        }
      };
    }

    private IStringModelBody createNameBody() {
      return new IStringModelBody() {
        @Override
        public Document initialize(final IPropertyModelContext context) {
          return new PlainDocument();
        }

        @Override
        public void ok(final Document document) {
          container.setName(DocumentHelper.getText(document));
        }
      };
    }
  }

  private class GrabberAction extends AbstractAction {

    public GrabberAction() {
      super();
      putValue(Action.NAME, "Grabb DB");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      ProgressDialog dialog = ProgressDialog.create(new IProgressRunnable() {

        private volatile List<CardDescription> allCards = new LinkedList<CardDescription>();

        @Override
        public void done() {
          dbAccess.saveAllCardDescription(allCards);
          System.out.println("isEDT " + SwingUtilities.isEventDispatchThread());
        }

        @Override
        public void run(final IProgressMonitor monitor) throws Exception {
          monitor.setMessage("Grabbing DB...");
          GrabberJsoup grabberJsoup = new GrabberJsoup("English");
          grabberJsoup.grabAllEditions(new IGrabberListener() {

            int counter = 0;

            @Override
            public void grabbed(final CardDescription description) {
              allCards.add(description);
//              SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                  System.out.println("saving card " + description);
//                  dbAccess.saveAllCardDescription(Arrays.asList(description));
//                }
//              });
              monitor.setMessage("Grabbed card " + description.getName());
              monitor.step(counter++);
            }

          });
        }
      });
      dialog.setMin(0);
      dialog.setMax(249);
      dialog.start();
    }
  }
}
