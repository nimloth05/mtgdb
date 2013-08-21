package org.mtgdb.ui.util.dialog.properties;

import org.mtgdb.ui.util.dialog.properties.date.DateProperty;
import org.mtgdb.ui.util.dialog.properties.date.DatePropertyModel;
import org.mtgdb.ui.util.dialog.properties.date.IDatePropertyModelBody;
import org.mtgdb.ui.util.dialog.properties.list.IListPropertyBody;
import org.mtgdb.ui.util.dialog.properties.list.ListProperty;
import org.mtgdb.ui.util.dialog.properties.list.ListPropertyModel;
import org.mtgdb.ui.util.dialog.properties.selection.ISelectionPropertyModelBody;
import org.mtgdb.ui.util.dialog.properties.selection.SelectionProperty;
import org.mtgdb.ui.util.dialog.properties.selection.SelectionPropertyModel;
import org.mtgdb.ui.util.dialog.properties.string.IStringModelBody;
import org.mtgdb.ui.util.dialog.properties.string.StringModel;
import org.mtgdb.ui.util.dialog.properties.string.StringProperty;

/**
 * @author Sandro
 */
public final class PropertyFactory implements IPropertiesFactory {

  private final PropertyModelContext fContext;

  public PropertyFactory() {
    fContext = new PropertyModelContext();
  }

  public PropertyFactory(PropertyFactory factory) {
    fContext = factory.getContext();
  }

  @Override
  public StringProperty stringProperty(final String id, final IStringModelBody body) {
    StringModel model = new StringModel(body, fContext);
    fContext.register(id, model);
    return new StringProperty(id, model);
  }

  public ListProperty listProperty(final String id, final IListPropertyBody body) {
    ListPropertyModel model = new ListPropertyModel(body, fContext);
    fContext.register(id, model);
    return new ListProperty(id, model);
  }

  public DateProperty dateProperty(final String id, final IDatePropertyModelBody body) {
    DatePropertyModel model = new DatePropertyModel(fContext, body);
    fContext.register(id, model);
    return new DateProperty(id, model);
  }

  public SelectionProperty selectionProperty(final String id, final ISelectionPropertyModelBody<?> body) {
    SelectionPropertyModel model = new SelectionPropertyModel(fContext, body);
    fContext.register(id, model);
    return new SelectionProperty(id, model);
  }

  private PropertyModelContext getContext() {
    return fContext;
  }
}
