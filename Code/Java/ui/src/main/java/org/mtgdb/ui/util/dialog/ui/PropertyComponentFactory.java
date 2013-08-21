package org.mtgdb.ui.util.dialog.ui;

import net.miginfocom.swing.MigLayout;
import org.mtgdb.ui.util.components.time.DateSpinner;
import org.mtgdb.ui.util.dialog.properties.IProperty;
import org.mtgdb.ui.util.dialog.properties.IPropertyVisitor;
import org.mtgdb.ui.util.dialog.properties.date.IDateProperty;
import org.mtgdb.ui.util.dialog.properties.list.IDoubleClickHandler;
import org.mtgdb.ui.util.dialog.properties.list.IListProperty;
import org.mtgdb.ui.util.dialog.properties.selection.ISelectionProperty;
import org.mtgdb.ui.util.dialog.properties.string.IStringProperty;
import org.mtgdb.ui.util.models.DocumentHelper;
import org.mtgdb.ui.util.models.ListModelComboBoxModelAdapter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Sandro
 */
public final class PropertyComponentFactory implements IPropertyVisitor {

  private static class DoubleClickAdapter extends MouseAdapter {

    private IDoubleClickHandler fHandler;

    public DoubleClickAdapter(final IDoubleClickHandler handler) {
      fHandler = handler;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
      if (e.getClickCount() == 2) {
        JList list = (JList) e.getSource();
        if (fHandler != null) fHandler.doubleClicked(list.getSelectedValue());
      }
    }
  }

  private static class SpinnerChangeListener implements ChangeListener {

    private IDateProperty fProperty;

    public SpinnerChangeListener(IDateProperty property) {
      fProperty = property;
    }

    @Override
    public void stateChanged(final ChangeEvent e) {
      JSpinner source = (JSpinner) e.getSource();
      fProperty.getModel().setDate((Date) source.getValue());
    }
  }

  public static JComponent createComponent(final IProperty property) {
    PropertyComponentFactory factory = new PropertyComponentFactory();
    property.visit(factory);
    return factory.getComponent();
  }

  private JComponent fComponent;

  private PropertyComponentFactory() {
  }

  @Override
  public void accept(final IStringProperty property) {
    JFormattedTextField.AbstractFormatter formatter = property.getFormatter();
    if (formatter == null) {
      JTextField field = new JTextField();
      field.setDocument(property.getComponentModel());
      fComponent = field;
      return;
    }

    final String currentText = DocumentHelper.getText(property.getComponentModel());
    Object value;
    try {
      value = formatter.stringToValue(currentText);
    } catch (ParseException e) {
      value = null;
    }

    JFormattedTextField textField = new JFormattedTextField(new DefaultFormatterFactory(formatter));
    textField.setDocument(property.getComponentModel());
    textField.setValue(value);
    fComponent = textField;
  }

  @Override
  public void accept(final IListProperty property) {
    JPanel panel = new JPanel(new MigLayout("ins 0"));
    JList list = new JList(property.getModel().getListModel());
    list.addMouseListener(new DoubleClickAdapter(property.getDoubleClickHandler()));
    list.setSelectionModel(property.getModel().getListSelectionModel());
    panel.add(new JScrollPane(list), "push, grow, wrap");
    panel.add(new JButton(property.getModel().getAddAction()), "align right, split 2");
    panel.add(new JButton(property.getModel().getRemoveAction()));

    fComponent = panel;
  }

  @Override
  public void accept(final IDateProperty dateProperty) {
    final DateSpinner spinner = new DateSpinner();
    spinner.setLenient(true);
    if (dateProperty.getFormat() != null) spinner.setFormat(dateProperty.getFormat());
    spinner.setValue(dateProperty.getModel().getDate());
    spinner.addChangeListener(new SpinnerChangeListener(dateProperty));
    fComponent = spinner;
  }

  @Override
  public void accept(final ISelectionProperty selectionProperty) {
    JComboBox comboBox = new JComboBox();
    comboBox.setModel(new ListModelComboBoxModelAdapter(selectionProperty.getModel().getListModel(), selectionProperty.getModel().getSelectionModel()));
    fComponent = comboBox;
  }

  public JComponent getComponent() {
    return fComponent;
  }
}
