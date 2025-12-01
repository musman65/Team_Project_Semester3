package controllers;

/*
TODO BY: Aswinth & Sahel (work on it one at a time)
Coordinates application flow, connecting user
actions in the UI to updates in the model and 
simulation engine.
*/

import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    GraphsController graphsController;
    
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
        startButton.setDisable(true);
        heightTextField.setOnKeyReleased(event -> {
            if (!(heightTextField.getText().equals("")) && !(weightTextField.getText().equals("")) && !(timeTextField.getText().equals(""))) {
            startButton.setDisable(false);
        }
        else {
            startButton.setDisable(true);
        }
        });
        
        weightTextField.setOnKeyReleased(event -> {
            if (!(heightTextField.getText().equals("")) && !(weightTextField.getText().equals("")) && !(timeTextField.getText().equals(""))) {
            startButton.setDisable(false);
        }
        else {
            startButton.setDisable(true);
        }
        });
        
        timeTextField.setOnKeyReleased(event -> {
            if (!(heightTextField.getText().equals("")) && !(weightTextField.getText().equals("")) && !(timeTextField.getText().equals(""))) {
            startButton.setDisable(false);
        }
        else {
            startButton.setDisable(true);
        }
        });
       
        
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
    void displayGraphsClicked(MouseEvent event) throws IOException {
        if (this.graphsController == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Graphs.fxml"));
            Parent root = loader.load();
            this.graphsController = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Graphs");
            stage.setScene(new Scene(root));

            this.graphsController.setStage(stage);

            stage.show();
            
        } else {
            this.graphsController.getStage().show();
        }
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
        timeline.stop();
        timeline = null;
        engine = null;
        graphsController = null;
        tableOfData.getItems().clear();
        pauseButton.setText("Pause");
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
        
        
        // Input verification to be done here by Sahel What to verify:
        /*
        If all fields are numbers
        Height should be bigger than 2000m
        Mass should be bigger than 50kg and lower than 150kg
        Time step should be bigger than 0.01 and smaller than 0.1
        If not, make an alert box pop up!
        */
        
        double mass = Double.parseDouble(diverMass);
        double deltaTime = Double.parseDouble(timeStep);
        double startPos = Double.parseDouble(startingPosition);
        
        if (engine == null) {
            SimulationParameters params = new SimulationParameters(dragFactor, deltaTime, mass);
            Skydiver diver = new Skydiver(startPos, 0.0, -9.8, params);
            engine = new SimulationEngine(diver);
        }
        
        if (timeline == null) {
            timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
                double timeF = engine.getTimeframe();
        
                double[] row = engine.computationOfOneRow();
                if (row[0] <= 0) {
                    timeline.stop();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ResultsSummary.fxml"));
                    try {
                        Parent root = loader.load();
                        ResultsController rc = loader.getController();
                        System.out.println(timeF);
                        rc.setTimeTaken(timeF);
                        Stage stage = new Stage();
                        stage.setTitle("Hooray!");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException ex) {
                        System.getLogger(SimulationController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
                
                if (graphsController != null) {
                    graphsController.addPointToGraphs(timeF, row[2], row[1], row[0]);
                }
                
                SimulationResults sr = new SimulationResults(timeF, row[0], row[1], row[2], row[3]);
                tableOfData.scrollTo(tableOfData.getItems().size());
                tableOfData.getItems().add(sr);
            }));
            
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }
    @FXML
    private void returnbtn(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/StartScreen.fxml"));
        stage.getScene().setRoot(root);
    }
}
