/**
 * 
 */
package org.mtgdb.ui.util.components.sliderspinner;

import org.mtgdb.ui.util.models.SpinnerBoundedRangeModel;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class SpinnerBoundedRangeModelEditor extends JSpinner.DefaultEditor {

  private static class NumberEditorFormatter extends NumberFormatter {

    private final SpinnerBoundedRangeModel fModel;

    NumberEditorFormatter(SpinnerBoundedRangeModel model, NumberFormat format) {
      super(format);
      this.fModel = model;
      setValueClass(model.getValue().getClass());
    }

    @Override
    public Comparable<?> getMaximum() {
      return fModel.getMaximum();
    }

    @Override
    public Comparable<?> getMinimum() {
      return fModel.getMinimum();
    }

    @Override
    public void setMaximum(Comparable max) {
      fModel.setMaximum((Integer)max);
    }

    @Override
    public void setMinimum(Comparable minimum) {
      fModel.setMinimum((Integer)minimum);
    }

  }

  //We need a DecimalFormat with a Constructor that takes a local!
//  public static String getDefaultPattern(Locale locale) {
//    ResourceBundle bundle = LocaleData.getNumberFormatData(locale);
//    String[] all = bundle.getStringArray("NumberPatterns"); //$NON-NLS-1$
//    return all[0];
//  }
//
//  public SpinnerBoundedRangeModelEditor(JSpinner spinner) {
//    this(spinner, getDefaultPattern(spinner.getLocale()));
//  }

  public SpinnerBoundedRangeModelEditor(JSpinner spinner, String decimalFormatPattern) {
    this(spinner, new DecimalFormat(decimalFormatPattern));
  }

  private SpinnerBoundedRangeModelEditor(JSpinner spinner, DecimalFormat format) {
    super(spinner);
    if (!(spinner.getModel() instanceof SpinnerBoundedRangeModel)) {
      throw new IllegalArgumentException("not a SpinnerBoundedRangeModel");  //$NON-NLS-1$
    }

    SpinnerBoundedRangeModel model = (SpinnerBoundedRangeModel) spinner.getModel();
    NumberFormatter formatter = new NumberEditorFormatter(model, format);
    DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter);

    JFormattedTextField formattedTextField = getTextField();
    formattedTextField.setEditable(true);
    formattedTextField.setFormatterFactory(factory);
    formattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);

    setColumnWidth(model, formatter, formattedTextField);
  }

  public DecimalFormat getFormat() {
    return (DecimalFormat) ((NumberFormatter) (getTextField().getFormatter())).getFormat();
  }

  public SpinnerBoundedRangeModel getModel() {
    return (SpinnerBoundedRangeModel) (getSpinner().getModel());
  }

  private void setColumnWidth(SpinnerBoundedRangeModel model, NumberFormatter formatter, JFormattedTextField formatedTextField) {
    try {
      String maxString = formatter.valueToString(model.getMinimum());
      String minString = formatter.valueToString(model.getMaximum());
      formatedTextField.setColumns(Math.max(maxString.length(), minString.length()));
    }
    catch (ParseException e) {}
  }

}