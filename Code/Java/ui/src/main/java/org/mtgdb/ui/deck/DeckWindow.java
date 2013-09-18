package org.mtgdb.ui.deck;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import net.miginfocom.swing.MigLayout;
import org.mtgdb.ui.card.MagicCardPanel;
import org.mtgdb.ui.util.frame.FrameFactory;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Michael Sacher
 * @author Sandro Orlando
 */
public final class DeckWindow {

  private final JFrame frame;
  private final DeckWindowModel model;

  public DeckWindow(final DeckWindowModel model) {
    frame = FrameFactory.createCenteredFrame("Deck Administration", 1000, 700);
    this.model = model;
    createContentArea();
  }

  private void createContentArea() {
    frame.getContentPane().setLayout(new BorderLayout());

    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.add("Cards", createCardPanel());
    tabbedPane.add("Charts", createChartPanel());

    frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
  }

  private Component createCardPanel() {
    MagicCardPanel deckCardPanel = new MagicCardPanel(model.getDeckCardModel().getDeckCardsModel());
    MagicCardPanel cardDatabasePanel = new MagicCardPanel(model.getDeckCardModel().getCardDatabaseModel());
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, deckCardPanel.getPanel(), cardDatabasePanel.getPanel());
    splitPane.setDividerLocation(0.5);
    return splitPane;
  }

  private JPanel createChartPanel() {
    JPanel panel = new JPanel();
    final JFXPanel jfxPanelPie = new JFXPanel();
    final JFXPanel jfxPanelManaCurve = new JFXPanel();
    panel.setLayout(new MigLayout(""));

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
    return panel;
  }

  private BarChart createManaCurveChart() {
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

    bc.setTitle("Mana Curve");
    xAxis.setLabel("Mana");
    yAxis.setLabel("Count");

    bc.setLegendVisible(false);
    final AtomicReference<ObservableList<XYChart.Series<String, Number>>> holder = new AtomicReference<>();
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        @Override
        public void run() {
          holder.set(model.getChartModel().getManaCurveData());
        }
      });
    } catch (InterruptedException e) {
      Thread.interrupted();
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    bc.setData(holder.get());
    return bc;
  }

  public void show() {
    frame.setVisible(true);

  }

  private PieChart createPieChart() {
    final PieChart chart = new PieChart(model.getChartModel().getTypeChartData());
    chart.setTitle("Deck Contents");
    chart.setLegendVisible(false);
    return chart;
  }
}
