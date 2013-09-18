package org.mtgdb.ui.deck;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import org.mtgdb.model.Deck;
import org.mtgdb.services.ServiceManager;

import java.util.Map;

/**
 * @author Sandro Orlando
 */
public final class ChartModel {

  private final EventBus bus;
  private Deck deck;
  private ObservableList<XYChart.Series<String, Number>> manaCurveData;
  private ObservableList<PieChart.Data> typeChartData;

  public static ChartModel create(final Deck deck) {
    ChartModel model = ServiceManager.get(ChartModel.class);
    model.init(deck);
    return model;
  }

  @Inject
  private ChartModel(final EventBus bus) {
    this.bus = bus;
  }

  public void init(final Deck deck) {
    this.deck = deck;
    this.manaCurveData = createManaCurveData();
    this.typeChartData = createTypeChartData();
  }

  public ObservableList<XYChart.Series<String, Number>> getManaCurveData() {
    return manaCurveData;
  }

  public ObservableList<PieChart.Data> getTypeChartData() {
    return typeChartData;
  }

  private ObservableList<XYChart.Series<String, Number>> createManaCurveData() {
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

  public ObservableList<PieChart.Data> createTypeChartData() {
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    Map<String, Integer> components = deck.calcDeckComponents();

    for (String key : components.keySet()) {
      pieChartData.add(new PieChart.Data(key, components.get(key)));
    }

    return pieChartData;
  }

}

