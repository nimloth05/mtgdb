package org.mtgdb.ui.util.models;

import javax.swing.*;
import javax.swing.event.*;

public class SpinnerBoundedRangeModel extends AbstractSpinnerModel {
  
  private final BoundedRangeModel fModel;

  public SpinnerBoundedRangeModel(final BoundedRangeModel model) {
    fModel = model;
    connectListener();
  }

  public int getMaximum() {
    return fModel.getMaximum();
  }
  
  public int getMinimum() {
    return fModel.getMinimum();
  }
  
  @Override
  public Object getNextValue() {
    return fModel.getValue() + 1;
  }
  
  @Override
  public Object getPreviousValue() {
    return fModel.getValue() - 1;
  }

  @Override
  public Object getValue() {
    return fModel.getValue();
  }

  public void setMaximum(int value) {
    fModel.setMaximum(value);
  }

  public void setMinimum(int value) {
    fModel.setMinimum(value);
  }

  @Override
  public void setValue(final Object value) {
    fModel.setValue((Integer)value);
  }

  private void connectListener() {
    fModel.addChangeListener(new ChangeListener() {

      @Override
      public void stateChanged(ChangeEvent e) {
        fireStateChanged();
      }
    });
  }
  
  

}
