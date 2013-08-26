package org.mtgdb.ui.util.dialog.ui;

import org.mtgdb.ui.util.dialog.properties.PropertyGroup;
import org.mtgdb.ui.util.frame.FrameUtil;
import org.mtgdb.ui.util.frame.dialog.AbstractDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * PropertyContainer for properties.
 */
public final class PropertiesDialog extends AbstractDialog {

  private PropertyGroup fGroup;

  public PropertiesDialog(Window parent, final PropertyGroup group) {
    super(parent);
    fGroup = group;
    createDialog();
  }

  @Override
  protected JPanel createContent() {
    PropertiesPane propertiesPane = new PropertiesPane(fGroup);
    return propertiesPane.getComponent();
  }

  @Override
  protected Action getOkAction() {
    return new AbstractAction() {

      {
        putValue(Action.NAME, "ok");
      }

      @Override
      public void actionPerformed(final ActionEvent e) {
        fGroup.ok();
        FrameUtil.close(fDialog);
      }
    };
  }

  @Override
  protected Action getEscapeAction() {
    return new AbstractAction() {

      {
        putValue(Action.NAME, "Abbrechen");
      }

      @Override
      public void actionPerformed(final ActionEvent e) {
        fGroup.cancel();
        FrameUtil.close(fDialog);
      }
    };
  }

  public void show() {
    fDialog.setVisible(true);
  }
}
