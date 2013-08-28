package org.mtgdb.ui.main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;
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

  public static void main(String[] args) throws IOException {
    DeckWindow window = new DeckWindow();
    window.show();
  }

  private void createContentArea() throws IOException {
    JPanel panel = new JPanel();
    final JFXPanel jfxPanelPie = new JFXPanel();
    final JFXPanel jfxPanelManaCurve = new JFXPanel();
    panel.setLayout(new MigLayout(""));

    panel.add(new JLabel("Your Library:"), "wrap");

    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        //javaFX operations should go here
        jfxPanelPie.setScene(new Scene(createPieChart()));
        jfxPanelManaCurve.setScene(new Scene(createManaCurveChart()));
      }
    });

    panel.add(jfxPanelPie, "wrap");
    panel.add(jfxPanelManaCurve, "");

//    final JTable table = new JTable();
//    table.setModel(model.getLibraryModel());
//    table.setSelectionModel(model.getTableSelectionModel());
//    final JScrollPane pane = new JScrollPane(table);
//    panel.add(pane, "grow, push");


    frame.getContentPane().add(panel, BorderLayout.CENTER);
  }

  private BarChart createManaCurveChart() {

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

    bc.setTitle("Mana Curve");
    xAxis.setLabel("Mana");
    yAxis.setLabel("Count");

    XYChart.Series series1 = new XYChart.Series();
    series1.setName("Mana");
    series1.getData().add(new XYChart.Data("0", 1));
    series1.getData().add(new XYChart.Data("1", 2));
    series1.getData().add(new XYChart.Data("2", 4));
    series1.getData().add(new XYChart.Data("3", 5));
    series1.getData().add(new XYChart.Data("4", 3));
    series1.getData().add(new XYChart.Data("5", 3));
    series1.getData().add(new XYChart.Data("6", 3));
    series1.getData().add(new XYChart.Data("7", 1));

    bc.getData().add(series1);
//    Scene scene = new Scene(bc, 800, 600);
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

    ObservableList<PieChart.Data> pieChartData =
      FXCollections.observableArrayList(
        new PieChart.Data("Creatures", 13),
        new PieChart.Data("Instants", 25),
        new PieChart.Data("Sorceries", 10),
        new PieChart.Data("Standard Lands", 22),
        new PieChart.Data("Non Standard Lands", 30));
    final PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Deck Contents");
    chart.setLegendVisible(false);
    return chart;
  }
}
