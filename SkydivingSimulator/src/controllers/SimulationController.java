package controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
TODO BY: Aswinth & Sahel (work on it one at a time)
Coordinates application flow, connecting user
actions in the UI to updates in the model and 
simulation engine.
*/

import java.util.Arrays;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import models.SimulationEngine;
import models.SimulationParameters;
import models.SimulationResults;
import models.Skydiver;

/**
 *
 * @author 2472557
 */
public class SimulationController {
     SimulationEngine engine;
    Timeline timeline;
    
    @FXML
    private TableColumn<SimulationResults, Double> accelerationCol;

    @FXML
    private Button displayButton;

    @FXML
    private ToggleGroup dragFactorToggleGroup;

    @FXML
    private TableColumn<SimulationResults, Double> forceCol;

    @FXML
    private TableColumn<SimulationResults, Double> heightCol;

    @FXML
    private TextField heightTextField;

    @FXML
    private RadioButton lowRadioButton;

    @FXML
    private RadioButton normalRadioButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button startButton;

    @FXML
    private RadioButton strongRadioButton;
    
    @FXML
    private TableView<SimulationResults> tableOfData;

    @FXML
    private TableColumn<SimulationResults, Double> timeCol;

    @FXML
    private TextField timeTextField;

    @FXML
    private TableColumn<SimulationResults, Double> velocityCol;

    @FXML
    private TextField weightTextField;
    
    @FXML
    public void initialize() {
        timeCol.setReorderable(false);
        heightCol.setReorderable(false);
        accelerationCol.setReorderable(false);
        accelerationCol.setReorderable(false);
        forceCol.setReorderable(false);
        
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        velocityCol.setCellValueFactory(new PropertyValueFactory<>("velocity"));
        accelerationCol.setCellValueFactory(new PropertyValueFactory<>("acceleration"));
        forceCol.setCellValueFactory(new PropertyValueFactory<>("force"));
    }
    
    @FXML
    void displayGraphsClicked(MouseEvent event) {

    }

    @FXML
    void pauseButtonClicked(MouseEvent event) {
        if (timeline != null) {
            if (pauseButton.getText().equals("Pause")) {
                pauseButton.setText("Play");
                timeline.pause();
            } else if (pauseButton.getText().equals("Play")) {
                pauseButton.setText("Pause");
                timeline.play();
            }
        }
    }

    @FXML
    void resetButtonClicked(MouseEvent event) {

    }

    @FXML
    void returnToMenuClicked(MouseEvent event) {

    }

    @FXML
    void startButtonClicked(MouseEvent event) {
        String startingPosition = heightTextField.getText();
        String diverMass = weightTextField.getText();
        String timeStep = timeTextField.getText();
        
        double dragFactor = 1;
        
        ToggleButton t = (ToggleButton) dragFactorToggleGroup.getSelectedToggle();
        
        dragFactor = switch (t.getText()) {
            case "Low" -> 0.5;
            case "Normal" -> 1;
            case "Strong" -> 1.5;
            default -> 1;
        };
        
        double mass = Double.parseDouble(diverMass);
        double deltaTime = Double.parseDouble(timeStep);
        double startPos = Double.parseDouble(startingPosition);
        
        if (engine == null) {
            SimulationParameters params = new SimulationParameters(dragFactor, deltaTime, mass);
            Skydiver diver = new Skydiver(startPos, 0.0, -9.8, params);
            engine = new SimulationEngine(diver);
        }
        
        if (timeline == null) {
            timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                double timeF = engine.getTimeframe();
        
                double[] row = engine.computationOfOneRow();
                System.out.println(Arrays.toString(row));
                SimulationResults sr = new SimulationResults(timeF, row[0], row[1], row[2], row[3]);
                tableOfData.scrollTo(tableOfData.getItems().size());
                tableOfData.getItems().add(sr);
            }));
            
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }
}
