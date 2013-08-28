package org.mtgdb.ui.main.action;

import org.mtgdb.model.Container;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;
import org.mtgdb.ui.util.dialog.properties.PropertyFactory;
import org.mtgdb.ui.util.dialog.properties.PropertyGroup;
import org.mtgdb.ui.util.dialog.properties.string.IStringModelBody;
import org.mtgdb.ui.util.dialog.ui.PropertiesDialog;
import org.mtgdb.ui.util.models.DocumentHelper;

import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;

/**
 * @author Sandro Orlando
 */
public class ContainerPropertiesDialog {

  private Container container = new Container();

  public void show(Window parent, final Container container) {
    this.container = container;

    PropertyGroup group = new PropertyGroup("group1");
    PropertyFactory factory = new PropertyFactory();
    group
      .add(factory.stringProperty("name", createNameBody()).setLabel("Name"))
      .add(factory.stringProperty("description", createDescriptionBody()).setLabel("Description"));

    PropertiesDialog dialog = new PropertiesDialog(parent, group);
    dialog.show();
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
