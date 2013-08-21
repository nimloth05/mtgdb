package org.mtgdb.ui.util.dialog.ui;

import net.miginfocom.swing.MigLayout;
import org.mtgdb.ui.util.dialog.properties.IProperty;
import org.mtgdb.ui.util.dialog.properties.PropertyGroup;

import javax.swing.*;

/**
 * @author Sandro
 */
public final class PropertiesPane {

  private PropertyGroup fGroup;
  private JPanel fPanel;

  public PropertiesPane(PropertyGroup group) {
    if (group == null) throw new NullPointerException("group");
    fGroup = group;
    initComponents();
  }

  private void initComponents() {
    fPanel = new JPanel();
    fPanel.setLayout(new MigLayout("wrap 2", "[][fill, grow]", ""));
    for (IProperty property : fGroup.getProperties()) {
      JLabel label = new JLabel();
      label.setText(property.getLabel());
      fPanel.add(label, "top");
      JComponent component = PropertyComponentFactory.createComponent(property);
      fPanel.add(component);
    }
  }

  public JPanel getComponent() {
    return fPanel;
  }

  public void ok() {
    for (IProperty property : fGroup.getProperties()) {
      property.getModel().ok();
    }
  }
}
