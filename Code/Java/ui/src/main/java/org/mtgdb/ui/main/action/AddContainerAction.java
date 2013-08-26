package org.mtgdb.ui.main.action;

import com.google.inject.Inject;
import org.jdesktop.swingx.VerticalLayout;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.ITransaction;
import org.mtgdb.db.ITransactionRunnable;
import org.mtgdb.db.repository.ContainerRepository;
import org.mtgdb.model.Container;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;
import org.mtgdb.ui.util.dialog.properties.PropertyFactory;
import org.mtgdb.ui.util.dialog.properties.PropertyGroup;
import org.mtgdb.ui.util.dialog.properties.string.IStringModelBody;
import org.mtgdb.ui.util.dialog.ui.PropertiesPane;
import org.mtgdb.ui.util.frame.FrameFactory;
import org.mtgdb.ui.util.frame.FrameUtil;
import org.mtgdb.ui.util.models.DocumentHelper;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* @author Sandro Orlando
*/
public class AddContainerAction extends AbstractAction {

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
