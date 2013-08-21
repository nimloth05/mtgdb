package org.mtgdb.ui.util.dialog.properties;

/**
 *
 */
public abstract class AbstractProperty implements IProperty {

  private String fLabel;
  private String fDescription;
  protected String fId;

  public AbstractProperty(final String id) {
    fId = id;
  }

  public AbstractProperty setLabel(final String label) {
    fLabel = label;
    return this;
  }

  public AbstractProperty setDescription(final String description) {
    fDescription = description;
    return this;
  }

  public String getLabel() {
    return fLabel;
  }

  public String getDescription() {
    return fDescription;
  }

  public String getId() {
    return fId;
  }
}
