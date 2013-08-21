package org.mtgdb.ui.util.dialog.properties;


import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Sandro
 */
public final class PropertyGroup {

  private String fId;
  private Map<String, IProperty> fProperties = new LinkedHashMap<String, IProperty>();

  public PropertyGroup(final String id) {
    fId = id;
  }

  public String getId() {
    return fId;
  }

  public PropertyGroup add(IProperty property) {
    fProperties.put(property.getId(), property);
    return this;
  }

  public IProperty get(final String id) {
    return fProperties.get(id);
  }

  public Collection<IProperty> getProperties() {
    return Collections.unmodifiableCollection(fProperties.values());
  }

  public void ok() {
    for (IProperty property : getProperties()) {
      property.getModel().ok();
    }
  }
}