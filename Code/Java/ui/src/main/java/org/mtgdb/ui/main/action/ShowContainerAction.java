package org.mtgdb.ui.main.action;

import com.google.inject.Inject;
import org.jdesktop.swingx.renderer.DefaultListRenderer;
import org.jdesktop.swingx.renderer.StringValue;
import org.mtgdb.db.repository.IContainerRepository;
import org.mtgdb.model.Container;
import org.mtgdb.services.ServiceManager;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;
import org.mtgdb.ui.util.dialog.properties.PropertyFactory;
import org.mtgdb.ui.util.dialog.properties.PropertyGroup;
import org.mtgdb.ui.util.dialog.properties.list.IListPropertyBody;
import org.mtgdb.ui.util.dialog.ui.PropertiesDialog;
import org.mtgdb.ui.util.frame.FrameFactory;
import org.mtgdb.ui.util.models.ListModelAdapterList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sandro Orlando
 */
public final class ShowContainerAction extends AbstractAction {

  private IContainerRepository repository;

  @Inject
  public void ShowContainerAction(final IContainerRepository repository) {
    this.repository = repository;
    putValue(Action.NAME, "List of storage Containers");
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    PropertyGroup group = new PropertyGroup("showContainer");
    PropertyFactory factory = new PropertyFactory();

    group
      .add(factory
        .listProperty("showContainers", createContainerListBody())
        .setCellRenderer(createContainerCellRenderer())
        .setLabel("Stoage Containers"));

    PropertiesDialog dialog = new PropertiesDialog(FrameFactory.getMainFrame(), group);
    dialog.show();
  }

  private ListCellRenderer<Container> createContainerCellRenderer() {
    StringValue converter = new StringValue() {
      @Override
      public String getString(final Object value) {
        return ((Container)value).getName();
      }
    };
    return new DefaultListRenderer(converter);
  }

  private IListPropertyBody createContainerListBody() {
    return new IListPropertyBody() {

      private Collection<Container> newContainers = new LinkedList<>();
      public List<Container> removeContainers = new LinkedList<>();

      @Override
      public ListModel initializeList(final IPropertyModelContext context) {
        return new ListModelAdapterList<>(repository.getAll());
      }

      @Override
      public void addElement(final IPropertyModelContext context, final ListModel listModel) {
        ListModelAdapterList<Container> containerListModel = (ListModelAdapterList<Container>) listModel;
        final Container container = new Container();
        container.setName("New Container");
        containerListModel.add(container);
        newContainers.add(container);
        showContainerProperties(containerListModel, container);
      }

      private void showContainerProperties(final ListModelAdapterList<Container> containerListModel, final Container container) {
        ContainerPropertiesDialog dialog = ServiceManager.get(ContainerPropertiesDialog.class);
        dialog.show(FrameFactory.getMainFrame(), container);
        containerListModel.modelChanged(container);
      }

      @Override
      public void ok(final ListModel listModel) {
        repository.saveAll(newContainers);
        repository.removeAll(removeContainers);
      }

      @Override
      public void removeElement(final IPropertyModelContext context, final ListModel listModel, final int index) {
        ListModelAdapterList<Container> containerListModel = (ListModelAdapterList<Container>) listModel;
        final Container container = containerListModel.remove(index);
        final boolean newContainerRemoved = newContainers.remove(container);
        if (!newContainerRemoved) {
          removeContainers.add(container);
        }
      }
    };
  }
}
