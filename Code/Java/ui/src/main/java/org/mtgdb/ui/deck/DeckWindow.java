package org.mtgdb.ui.deck;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import net.miginfocom.swing.MigLayout;
import org.mtgdb.ui.util.frame.FrameFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author Michael Sacher
 */
public final class DeckWindow {
  private JFrame frame;
  private DeckWindowModel model;

  public DeckWindow(final DeckWindowModel model) {

  }


  private void createContentArea() throws IOException {
    JPanel panel = new JPanel();
    final JFXPanel jfxPanelPie = new JFXPanel();
    final JFXPanel jfxPanelManaCurve = new JFXPanel();
    panel.setLayout(new MigLayout(""));

    panel.add(new JLabel("Your Library:"), "wrap");

    //give control to javafx thread
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        //javaFX operations should go here
        jfxPanelPie.setScene(new Scene(createPieChart()));
        jfxPanelManaCurve.setScene(new Scene(createManaCurveChart()));
      }
    });

    panel.add(jfxPanelPie, "wrap");
    panel.add(jfxPanelManaCurve, "wrap");

    frame.getContentPane().add(panel, BorderLayout.CENTER);
  }

  private BarChart createManaCurveChart() {

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

    bc.setTitle("Mana Curve");
    xAxis.setLabel("Mana");
    yAxis.setLabel("Count");

    bc.setLegendVisible(false);
    bc.setData(model.getChartModel().getManaCurveChartData());
    return bc;
  }

  public void show() throws IOException {
    frame = FrameFactory.createCenteredFrame("MTG Administration", 1000, 700);
    FrameFactory.setMainFrame(frame);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.getContentPane().setLayout(new BorderLayout());
    createContentArea();
    frame.setVisible(true);

  }

  private PieChart createPieChart() {
    final PieChart chart = new PieChart(model.getChartModel().getTypeChartData());
    chart.setTitle("Deck Contents");
    chart.setLegendVisible(false);
    return chart;
  }
}
