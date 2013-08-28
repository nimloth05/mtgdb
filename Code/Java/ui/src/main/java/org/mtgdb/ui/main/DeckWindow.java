package org.mtgdb.ui.main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
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

  public static void main(String[] args) throws IOException {
    DeckWindow window = new DeckWindow();
    window.show();
  }

  private void createContentArea() throws IOException {
    JPanel panel = new JPanel();
    final JFXPanel jfxPanel = new JFXPanel();
    panel.setLayout(new MigLayout(""));

    panel.add(new JLabel("Your Library:"), "wrap");

           Platform.runLater(new Runnable() {
             @Override
             public void run() {
               //javaFX operations should go here
               Chart chart = createChart();
               jfxPanel.setScene(new Scene(chart));
             }
           });

    panel.add(jfxPanel, "");

//    final JTable table = new JTable();
//    table.setModel(model.getLibraryModel());
//    table.setSelectionModel(model.getTableSelectionModel());
//    final JScrollPane pane = new JScrollPane(table);
//    panel.add(pane, "grow, push");


    frame.getContentPane().add(panel, BorderLayout.CENTER);
  }

  public void show() throws IOException {
    frame = FrameFactory.createCenteredFrame("MTG Administration", 1000, 700);
    FrameFactory.setMainFrame(frame);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.getContentPane().setLayout(new BorderLayout());
    createContentArea();
    frame.setVisible(true);

  }

  private Chart createChart() {

    ObservableList<PieChart.Data> pieChartData =
      FXCollections.observableArrayList(
        new PieChart.Data("Creatures", 13),
        new PieChart.Data("Instants", 25),
        new PieChart.Data("Sorceries", 10),
        new PieChart.Data("Standard Lands", 22),
        new PieChart.Data("Non Standard Lands", 30));
    final PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Deck Contents");
    return chart;
  }
}
