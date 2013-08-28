package org.mtgdb.ui.main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.mtgdb.model.Deck;
import org.mtgdb.model.MagicCard;
import org.mtgdb.ui.util.frame.FrameFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

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

  private Integer[] calcManaCurve(Collection<MagicCard> magicCards) {
    Predicate predicateManaCost1 = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getConvertedManaCost() == 1;
      }
    };
    Predicate predicateManaCost2 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 2;
        }
      };
    Predicate predicateManaCost3 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 3;
        }
      };
    Predicate predicateManaCost4 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 4;
        }
      };
    Predicate predicateManaCost5 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 5;
        }
      };
    Predicate predicateManaCost6 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 6;
        }
      };
    Predicate predicateManaCost7 = new

      Predicate() {
        public boolean evaluate(Object object) {
          return ((MagicCard) object).getConvertedManaCost() == 7;
        }
      };
    Integer[] manaCurve = new Integer[10];

    Collection filtered;
    filtered = CollectionUtils.select(magicCards, predicateManaCost1);
    manaCurve[1] = filtered.size();
    filtered = CollectionUtils.select(magicCards, predicateManaCost2);
    manaCurve[2] = filtered.size();
    filtered = CollectionUtils.select(magicCards, predicateManaCost3);
    manaCurve[3] = filtered.size();
    filtered = CollectionUtils.select(magicCards, predicateManaCost4);
    manaCurve[4] = filtered.size();
    filtered = CollectionUtils.select(magicCards, predicateManaCost5);
    manaCurve[5] = filtered.size();
    filtered = CollectionUtils.select(magicCards, predicateManaCost6);
    manaCurve[6] = filtered.size();
    filtered = CollectionUtils.select(magicCards, predicateManaCost7);
    manaCurve[7] = filtered.size();
    return manaCurve;
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
        jfxPanelManaCurve.setScene(new Scene(createManaCurveChart(deck.getCards())));
      }
    });

//Predicate<T> predicate = new Predicate<>();
    panel.add(jfxPanelPie, "wrap");
    panel.add(jfxPanelManaCurve, "wrap");

//    final JTable table = new JTable();
//    table.setModel(model.getLibraryModel());
//    table.setSelectionModel(model.getTableSelectionModel());
//    final JScrollPane pane = new JScrollPane(table);
//    panel.add(pane, "grow, push");


    frame.getContentPane().add(panel, BorderLayout.CENTER);
  }

  private BarChart createManaCurveChart(Collection<MagicCard> magicCards) {

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

    bc.setTitle("Mana Curve");
    xAxis.setLabel("Mana");
    yAxis.setLabel("Count");

    XYChart.Series series1 = new XYChart.Series();
    series1.setName("Mana");
    Integer[] manaCurve = this.calcManaCurve(magicCards);
    for (Integer index : manaCurve) {

      series1.getData().add(new XYChart.Data(index.toString(), manaCurve[index]));
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
