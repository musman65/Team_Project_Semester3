package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * Controller responsible for managing and updating the graphs window.
 * This class receives simulation data (time, acceleration, velocity, and position)
 * and plots them on three separate line charts:
 * The controller is created when the graph window is opened and receives
 * data points from the main SimulationController during runtime.
 * @author sinna
 */

public class GraphsController {
    private Stage stage;
    
    // Line series storing position (height) vs time values.
    private XYChart.Series<Number, Number> positionSeries = new XYChart.Series<>();
    // Line series storing velocity vs time values.
    private XYChart.Series<Number, Number> velocitySeries = new XYChart.Series<>();
    // Line series storing acceleration vs time values.
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
        // Add each series to its corresponding chart
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
    
    /**
     * Adds a new data point to all three graphs.
     * <p>
     * This method is called repeatedly during the simulation to visualize
     * how the skydiver's motion changes over time.
     *
     * @param t time in seconds
     * @param a acceleration at time t
     * @param v velocity at time t
     * @param x position (height) at time t
     */
    public void addPointToGraphs(double t, double a, double v, double x) {
        // Update the acceleration curve
        accelSeries.getData().add(new XYChart.Data<>(t, a));
        
        // Update the velocity curve
        velocitySeries.getData().add(new XYChart.Data<>(t, v));
        
        // Update the position curve
        positionSeries.getData().add(new XYChart.Data<>(t, x));
    }
    
}
