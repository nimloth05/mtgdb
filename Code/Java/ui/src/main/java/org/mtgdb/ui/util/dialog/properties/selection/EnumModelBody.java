package org.mtgdb.ui.util.dialog.properties.selection;

import org.jdesktop.swingx.combobox.EnumComboBoxModel;
import org.mtgdb.ui.util.dialog.properties.IPropertyModelContext;

import javax.swing.*;

/**
 *
 */
public abstract class EnumModelBody<T extends Enum<T>> implements ISelectionPropertyModelBody<T> {

  private final EnumComboBoxModel<T> fModel;

  public EnumModelBody(Class<T> enumClass) {
    fModel = new EnumComboBoxModel<T>(enumClass);
  }

  @Override
  public ListModel initialize(final IPropertyModelContext context) {
    return fModel;
  }
}
