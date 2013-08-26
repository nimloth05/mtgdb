package org.mtgdb.ui;

import com.google.inject.Inject;
import org.jdesktop.swingx.VerticalLayout;
import org.mtgdb.db.ContainerRepository;
import org.mtgdb.db.EditionRepository;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.ITransaction;
import org.mtgdb.db.ITransactionRunnable;
import org.mtgdb.db.MagicCardRepository;
import org.mtgdb.grabber.GrabberJsoup;
import org.mtgdb.grabber.IGrabberListener;
import org.mtgdb.model.CardDescription;
import org.mtgdb.model.Container;
import org.mtgdb.model.Edition;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.util.ImageLoader;
import org.mtgdb.ui.util.components.label.DefaultLabelModel;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

  private final IDatabaseConnection connection;
  @Inject
  private GrabberAction grabberAction;
  @Inject
  private AddContainerAction addContainerAction;
  private DefaultLabelModel scanLabelModel = new DefaultLabelModel();
  private DefaultListSelectionModel tableSelectionModel = new DefaultListSelectionModel();
  private MockTableModel mockTableModel;

  @Inject
  public MainModel(final IDatabaseConnection connection) {
    this.connection = connection;
    connection.openDB();
    mockTableModel = ServiceManager.get(MockTableModel.class);
    tableSelectionModel.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(final ListSelectionEvent e) {
        CardDescription selectedCard = mockTableModel.getCard(tableSelectionModel.getLeadSelectionIndex());
        Icon scan = ImageLoader.loadAsIcon(selectedCard.getImageURL());
        scanLabelModel.setIcon(scan);
      }
    });
    tableSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tableSelectionModel.setSelectionInterval(0, 0);
  }

  public DefaultLabelModel getScanLabelModel() {
    return scanLabelModel;
  }

  public ListSelectionModel getTableSelectionModel() {
    return tableSelectionModel;
  }

  public void closeDB() {
    connection.closeDB();
  }

  public Action getGrabberAction() {
    return grabberAction;
  }

  public TableModel getLibraryModel() {
    return mockTableModel;
  }

  public Action getAddContainerAction() {
    return addContainerAction;
  }

  private static class AddContainerAction extends AbstractAction {

    private final IDatabaseConnection connection;
    private final ContainerRepository containerRepository;
    private Container container = new Container();

    @Inject
    public AddContainerAction(final IDatabaseConnection connection, final ContainerRepository containerRepository) {
      super();
      this.connection = connection;
      this.containerRepository = containerRepository;
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
          saveContainer();
          FrameUtil.close(dialog);
        }
      });
      dialog.getContentPane().add(okButton);

      dialog.setVisible(true);
    }

    private void saveContainer() {
      connection.execute(new ITransactionRunnable() {
        @Override
        public void run(final ITransaction transaction) throws Exception {
          containerRepository.save(transaction, container);
        }
      });
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

  private static class GrabberAction extends AbstractAction {

    private final IDatabaseConnection connection;
    private final MagicCardRepository magicCardRepository;
    private final EditionRepository editionRepository;

    @Inject
    public GrabberAction(final IDatabaseConnection connection, final MagicCardRepository magicCardRepository, final EditionRepository editionRepository) {
      super();
      this.connection = connection;
      this.magicCardRepository = magicCardRepository;
      this.editionRepository = editionRepository;
      putValue(Action.NAME, "Grabb DB");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      connection.execute(new ITransactionRunnable() {

        @Override
        public void run(final ITransaction transaction) throws Exception {
          magicCardRepository.deleteAll(transaction);
          editionRepository.deleteAll(transaction);
        }
      });

      ProgressDialog dialog = ProgressDialog.create(new IProgressRunnable() {

        private volatile List<CardDescription> allCards = new LinkedList<CardDescription>();

        @Override
        public void done() {
          saveCards();
        }

        private void saveCards() {
          connection.execute(new ITransactionRunnable() {
            @Override
            public void run(final ITransaction transaction) throws Exception {
              magicCardRepository.saveAll(transaction, allCards);
            }
          });
        }

        @Override
        public void run(final IProgressMonitor monitor) throws Exception {
          monitor.setMessage("Grabbing DB...");
          GrabberJsoup grabberJsoup = new GrabberJsoup("English");
          grabberJsoup.grabAllEditions(new IGrabberListener() {

            int counter = 0;

            @Override
            public void beginEdition(final Edition edition) {
              saveEdition(edition);
            }

            private void saveEdition(final Edition edition) {
              System.out.println("grabbing edition " + edition);
              connection.execute(new ITransactionRunnable() {
                @Override
                public void run(final ITransaction transaction) throws Exception {
                  editionRepository.save(transaction, edition);
                }
              });
            }

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

            @Override
            public void endEdition() {

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
