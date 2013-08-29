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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

  private Collection<MagicCard> genTestCardSet() {
    Collection<MagicCard> mockCards = new ArrayList<MagicCard>();
    MagicCard card1 = new MagicCard();
    card1.setType("Creature");
    card1.setConvertedManaCost(1);
    mockCards.add(card1);
    MagicCard card2 = new MagicCard();
    card2.setType("Creature");
    card2.setConvertedManaCost(2);
    mockCards.add(card2);


    MagicCard card3 = new MagicCard();
    card3.setType("Creature");
    card3.setConvertedManaCost(2);
    mockCards.add(card3);

    MagicCard card4 = new MagicCard();
    card4.setType("Sorcery");
    card4.setConvertedManaCost(7);
    mockCards.add(card4);

    MagicCard card5 = new MagicCard();
    card5.setType("Instant");
    card5.setConvertedManaCost(3);
    mockCards.add(card5);

    MagicCard card6 = new MagicCard();
    card6.setType("Instant");
    card6.setConvertedManaCost(4);
    mockCards.add(card6);

    MagicCard card7 = new MagicCard();
    card7.setType("Land");
    mockCards.add(card7);

    return mockCards;
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
        jfxPanelPie.setScene(new Scene(createPieChart(genTestCardSet())));
        jfxPanelManaCurve.setScene(new Scene(createManaCurveChart(genTestCardSet())));
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
    for (Integer index = 1; index < manaCurve.length-2; index++) {
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

  private Map<String, Integer> calcDeckComponents(Collection<MagicCard> magicCards) {
    Map<String, Integer> components = new HashMap<>();

    Predicate predicateCreature = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getType().contains("Creature");
      }
    };

    Predicate predicateInstant = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getType().contains("Instant");
      }
    };

    Predicate predicateSorcery = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getType().contains("Sorcery");
      }
    };

    Predicate predicateLand = new Predicate() {
      public boolean evaluate(Object object) {
        return ((MagicCard) object).getType().contains("Land");
      }
    };
    Collection filtered;

    filtered = CollectionUtils.select(magicCards, predicateCreature);
    components.put("Creatures", filtered.size());

    filtered = CollectionUtils.select(magicCards, predicateInstant);
    components.put("Instants", filtered.size());

    filtered = CollectionUtils.select(magicCards, predicateSorcery);
    components.put("Sorceries", filtered.size());

    filtered = CollectionUtils.select(magicCards, predicateLand);
    components.put("Lands", filtered.size());

    return components;
  }

  private PieChart createPieChart(Collection<MagicCard> magicCards) {

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    Map<String, Integer> components = calcDeckComponents(magicCards);
    for (String key : components.keySet()) {
      pieChartData.add(new PieChart.Data(key, components.get(key)));
    }

    final PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Deck Contents");
    chart.setLegendVisible(false);
    return chart;
  }
}
