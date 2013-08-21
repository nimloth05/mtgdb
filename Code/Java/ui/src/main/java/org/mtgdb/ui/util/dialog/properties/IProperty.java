package org.mtgdb.ui.util.dialog.properties;

/**
 * @author Sandro
 */
public interface IProperty {

  public IPropertyModel getModel();

  public String getId();

  public String getLabel();

  public String getDescription();

  public void visit(IPropertyVisitor visitor);
}