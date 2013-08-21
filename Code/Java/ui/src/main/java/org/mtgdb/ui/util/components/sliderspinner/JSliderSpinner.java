package org.mtgdb.ui.util.components.sliderspinner;

import net.miginfocom.swing.MigLayout;
import org.mtgdb.ui.util.models.SpinnerBoundedRangeModel;
import org.mtgdb.util.Constants;

import javax.swing.*;
import java.awt.*;

public class JSliderSpinner extends JComponent {
  
  private final BoundedRangeModel fModel;
  private JSlider fSlider;

  public JSliderSpinner(BoundedRangeModel model) {
    fModel = model;
    initComponents();
  }
  
  public JSlider getSlider() {
    return fSlider;
  }
  
  private JSlider createSlider() {
    fSlider = new JSlider();
    fSlider.setModel(fModel);
//    fSlider.setMajorTickSpacing(10);
//    fSlider.setMinorTickSpacing(5);
    fSlider.setPaintLabels(true);
    fSlider.setPaintTicks(true);
    fSlider.setPaintTrack(true);

    final Font font = new Font("Serif", Font.ITALIC, 12); //$NON-NLS-1$
    fSlider.setFont(font);

    return fSlider;
  }
  
  private JSpinner createSpinner() {
    final JSpinner spinner = new JSpinner();
    spinner.setModel(new SpinnerBoundedRangeModel(fModel));
    String pattern = "#,##0";
    
    if (fModel.getMinimum() < 0) pattern += ";-#,##0.###";
    spinner.setEditor(new SpinnerBoundedRangeModelEditor(spinner, pattern));

    return spinner;
  }
  
  private void initComponents() {
    setLayout(new MigLayout(Constants.EMPTY, "[grow, fill][]")); //$NON-NLS-1$
    add(createSlider());
    add(createSpinner());
  }

}
