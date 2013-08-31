package org.mtgdb.ui.main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import net.miginfocom.swing.MigLayout;
import org.mtgdb.model.Deck;
import org.mtgdb.ui.util.frame.FrameFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

/**
 * @author Michael Sacher
 */
public final class DeckWindow {
  private JFrame frame;
  private Deck deck;

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

    //give control to javafx thread
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        //javaFX operations should go here
        jfxPanelPie.setScene(new Scene(createPieChart()));
        jfxPanelManaCurve.setScene(new Scene(createManaCurveChart()));
      }
    });

//Predicate<T> predicate = new Predicate<>();
    panel.add(jfxPanelPie, "wrap");
    panel.add(jfxPanelManaCurve, "wrap");

//    final JTable table = new JTable();
//    table.setModel(model.getTableModel());
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
    Integer[] manaCurve = deck.calcManaCurve();
    for (Integer index = 1; index < manaCurve.length - 2; index++) {
      series1.getData().add(new XYChart.Data(index.toString(), manaCurve[index]));
//      System.out.println("index:"+index.toString());

    }
    bc.setLegendVisible(false);

    bc.getData().add(series1);
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

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    Map<String, Integer> components = deck.calcDeckComponents();

    for (String key : components.keySet()) {
      pieChartData.add(new PieChart.Data(key, components.get(key)));
    }

    final PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Deck Contents");
    chart.setLegendVisible(false);
    return chart;
  }
}
