package org.mtgdb.ui.util.dialog.properties;

import org.mtgdb.ui.util.dialog.properties.string.IStringModelBody;
import org.mtgdb.ui.util.dialog.properties.string.StringProperty;

/**
 * @author Sandro
 */
public interface IPropertiesFactory {

  public StringProperty stringProperty(final String id, IStringModelBody body);

}
