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
    private Button returnbtn;

    public void initialize() {
        // From this part ->
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
        
        if (heightTextField.getText().charAt(0) == '-' || height < 2000) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Wrong Height Input");
            error.setContentText("You entered: " + heightTextField.getText() + "\n Remember: Height must be bigger than 2000m.");
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

                if (graphsController != null) {
                    graphsController.addPointToGraphs(timeF, row[2], row[1], row[0]);
                }

                SimulationResults sr = new SimulationResults(timeF, row[0], row[1], row[2], row[3]);
                tableOfData.getItems().add(sr);
                tableOfData.scrollTo(tableOfData.getItems().size());
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
    
    private boolean isNumber(String s ) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
