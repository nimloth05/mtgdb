package org.mtgdb.ui.deck;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import org.mtgdb.model.Deck;

import java.util.Map;

/**
 * @author Sandro Orlando
 */
public final class ChartModel {

  private Deck deck;

  public ChartModel(final Deck deck) {
    this.deck = deck;
  }

  public ObservableList<XYChart.Series<String, Number>> getManaCurveChartData() {
    ObservableList<XYChart.Series<String, Number>> result = FXCollections.observableArrayList();
    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
    series1.setName("Mana");
    int[] manaCurve = deck.calcManaCurve();
    for (Integer index = 1; index < manaCurve.length - 2; index++) {
      series1.getData().add(new XYChart.Data<String, Number>(index.toString(), manaCurve[index]));
    }
    result.add(series1);
    return result;
  }

  public ObservableList<PieChart.Data> getTypeChartData() {
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    Map<String, Integer> components = deck.calcDeckComponents();

    for (String key : components.keySet()) {
      pieChartData.add(new PieChart.Data(key, components.get(key)));
    }

    return pieChartData;
  }

}

