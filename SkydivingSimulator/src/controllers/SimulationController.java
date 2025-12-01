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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
 * Main controller responsible for running the skydiving simulation.
 * This class:
 * Validates user inputs.
 * Initializes the simulation engine.
 * Updates graphs and the table in real time.
 * Controls simulation flow (start, pause, reset).
 * 
 * It acts as the central coordinator between the UI and the physics model.
 * @author 2472557
 */
public class SimulationController {

    // Core physics engine performing Euler-based calculations
    SimulationEngine engine;
    // Timeline controlling the real-time simulation loop.
    Timeline timeline;
    // Controller for the graph window (created only once).
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
    private Button returnbtn;

    /**
     * Initializes UI components when the FXML loads.
     * This method:
     * Disables Start button until all inputs are filled.
     * Sets up table columns for SimulationResults.
     */
    public void initialize() {
        // From this part ->
        
        // Disable start button until all text fields have values
        startButton.setDisable(true);
        heightTextField.setOnKeyReleased(event -> {
            if (!(heightTextField.getText().equals("")) && !(weightTextField.getText().equals("")) && !(timeTextField.getText().equals(""))) {
                startButton.setDisable(false);
            } else {
                startButton.setDisable(true);
            }
        });

        weightTextField.setOnKeyReleased(event -> {
            if (!(heightTextField.getText().equals("")) && !(weightTextField.getText().equals("")) && !(timeTextField.getText().equals(""))) {
                startButton.setDisable(false);
            } else {
                startButton.setDisable(true);
            }
        });

        timeTextField.setOnKeyReleased(event -> {
            if (!(heightTextField.getText().equals("")) && !(weightTextField.getText().equals("")) && !(timeTextField.getText().equals(""))) {
                startButton.setDisable(false);
            } else {
                startButton.setDisable(true);
            }
        });

        // -> To here was done by Sahel Assadi. 
        // -> There was a branch problem. Rest of commits are good and authored to the respective team member.
        
        // Column configuration for the results table
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

    /**
     * Opens or reveals the graph window displaying position, velocity,
     * and acceleration in real time.
     */
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

    /**
     * Toggles the simulation between paused and playing.
     */
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

    /**
     * Resets the simulation, clearing graphs, table, and engine state.
     */
    @FXML
    void resetButtonClicked(MouseEvent event) {
        timeline.stop();
        timeline = null;
        engine = null;
        graphsController = null;
        tableOfData.getItems().clear();
        pauseButton.setText("Pause");
    }

    /**
     * Validates user input, initializes simulation, and starts the timeline loop.
     */
    @FXML
    void startButtonClicked(MouseEvent event) {
        
        if (!(isNumber(heightTextField.getText()))) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Wrong Height Input");
            error.setContentText("You entered: " + heightTextField.getText() + "\n Remember: Height must be a number.");
            error.show();
            return;
        }
        
        if (!(isNumber(weightTextField.getText()))) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Wrong Weight Input");
            error.setContentText("You entered: " + weightTextField.getText() + "\nRemember: Weight must be a number.");
            error.show();
            return;
        }
        
        if (!(isNumber(timeTextField.getText()))) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Wrong Time Input");
            error.setContentText("You entered: " + timeTextField.getText() + "\nRemember: Time must be bigger a number.");
            error.show();
            return;
        }
        
        Double height = Double.parseDouble(heightTextField.getText());
        Double weight = Double.parseDouble(weightTextField.getText());
        Double time = Double.parseDouble(timeTextField.getText());
        
        if (heightTextField.getText().charAt(0) == '-' || height < 3000) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Wrong Height Input");
            error.setContentText("You entered: " + heightTextField.getText() + "\n Remember: Height must be bigger than 3000m.");
            error.show();
            return;
        }
        
        if (weightTextField.getText().charAt(0) == '-' || weight < 50 || weight > 150) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Wrong Weight Input");
            error.setContentText("You entered: " + weightTextField.getText() + "\nRemember: Weight must be bigger than \n50kg or lower than 150kg.");
            error.show();
            return;
        }
        
        if (timeTextField.getText().charAt(0) == '-' || time < 0.01 || time > 0.1) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Wrong Time Input");
            error.setContentText("You entered: " + timeTextField.getText() + "\nRemember: Time must be bigger than 0.01 and less than 0.1.");
            error.show();
            return;
        } 
        
        String startingPosition = heightTextField.getText();
        String diverMass = weightTextField.getText();
        String timeStep = timeTextField.getText();

        double dragFactor = 1;

        ToggleButton t = (ToggleButton) dragFactorToggleGroup.getSelectedToggle();

        dragFactor = switch (t.getText()) {
            case "Low" ->
                0.5;
            case "Normal" ->
                1;
            case "Strong" ->
                1.5;
            default ->
                1;
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
            timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
                double timeF = engine.getTimeframe();

                double[] row = engine.computationOfOneRow();
                
                // When height ≤ 0 → skydiver has landed
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
                    return;
                }

                // Update graphs if graph window is open
                if (graphsController != null) {
                    graphsController.addPointToGraphs(timeF, row[2], row[1], row[0]);
                }

                // Update table
                SimulationResults sr = new SimulationResults(timeF, row[0], row[1], row[2], row[3]);
                tableOfData.getItems().add(sr);
                tableOfData.scrollTo(tableOfData.getItems().size());
            }));

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }

    }

     /**
     * Returns user to the main start screen.
     */
    @FXML
    private void returnbtn(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/StartScreen.fxml"));
        stage.getScene().setRoot(root);
    }
    
    private boolean isNumber(String s ) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
