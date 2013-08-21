package org.mtgdb.ui.util.dialog.properties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sandro
 */
public final class PropertyModelContext implements IPropertyModelContext {

  private Map<String, IPropertyModel> fModels = new HashMap<String, IPropertyModel>();

  public void register(final String id, IPropertyModel model) {
    fModels.put(id, model);
  }

  @Override
  public IPropertyModel getModel(final String id) {
    return fModels.get(id);
  }
}