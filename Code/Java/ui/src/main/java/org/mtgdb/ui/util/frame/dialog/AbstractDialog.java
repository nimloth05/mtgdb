package org.mtgdb.ui.util.frame.dialog;

import net.miginfocom.swing.MigLayout;
import org.mtgdb.ui.util.frame.FrameFactory;
import org.mtgdb.ui.util.frame.FrameUtil;
import org.mtgdb.util.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog with ok and cancel buttons. The dialog will always be created centered in the screen.
 */
public abstract class AbstractDialog {

  protected JDialog fDialog;
  private Window fParent;

  public AbstractDialog(Window parent) {
    fParent = parent;
  }

  protected void createDialog() {
    fDialog = FrameFactory.createCenteredDialog(fParent, getTitle(), getSize());
    fDialog.getContentPane().setLayout(new BorderLayout());

    JPanel panel = createContent();
    fDialog.getContentPane().add(panel, BorderLayout.CENTER);

    JPanel buttonBar = createButtonPanel();
    fDialog.getContentPane().add(buttonBar, BorderLayout.SOUTH);
  }

  protected JPanel createButtonPanel() {
    JPanel buttonPanel = new JPanel(new MigLayout("fillx, ins dialog"));

    final JButton okButton = new JButton(getOkAction());
    fDialog.getRootPane().setDefaultButton(okButton);
    buttonPanel.add(okButton, "tag ok, right, span, split, sg button");

    final Action escapeAction = getEscapeAction();
    final JButton button = new JButton(escapeAction);
    FrameUtil.connectEscapeWithButton(button, escapeAction);
    buttonPanel.add(button, "tag cancel, bottom, sg button");

    return buttonPanel;
  }

  protected abstract JPanel createContent();
  protected abstract Action getOkAction();
  protected abstract Action getEscapeAction();

  public String getTitle() {
    return Constants.EMPTY;
  }

  private Dimension getSize() {
    return new Dimension(500, 400);
  }

  public String getLayoutConstraint() {
    return Constants.EMPTY;
  }
}
