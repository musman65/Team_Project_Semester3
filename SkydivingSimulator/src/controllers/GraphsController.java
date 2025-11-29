package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GraphsController {
    private Stage stage;
    private XYChart.Series<Number, Number> positionSeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> velocitySeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> accelSeries = new XYChart.Series<>();
    
    @FXML
    private NumberAxis accel;

    @FXML
    private LineChart<Number, Number> accelChart;

    @FXML
    private NumberAxis pos;

    @FXML
    private LineChart<Number, Number> posChart;

    @FXML
    private NumberAxis speed;

    @FXML
    private LineChart<Number, Number> speedChart;
    
    @FXML
    public void initialize() {
        accelChart.getData().add(accelSeries);
        speedChart.getData().add(velocitySeries);
        posChart.getData().add(positionSeries);
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
    
    public void addPointToGraphs(double t, double a, double v, double x) {
        accelSeries.getData().add(new XYChart.Data<>(t, a));
        velocitySeries.getData().add(new XYChart.Data<>(t, v));
        positionSeries.getData().add(new XYChart.Data<>(t, x));
    }
    
}
