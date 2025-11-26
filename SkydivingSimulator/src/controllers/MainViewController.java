/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 6321596
 */
public class MainViewController implements Initializable {

    @FXML
    private TableColumn<?, ?> timeCol;
    @FXML
    private TableColumn<?, ?> heightCol;
    @FXML
    private TableColumn<?, ?> velocityCol;
    @FXML
    private TableColumn<?, ?> accelerationCol;
    @FXML
    private TableColumn<?, ?> forceCol;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private RadioButton lowRadioButton;
    @FXML
    private ToggleGroup dragFactorToggleGroup;
    @FXML
    private RadioButton normalRadioButton;
    @FXML
    private RadioButton strongRadioButton;
    @FXML
    private Button startButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button displayButton;
    @FXML
    private Button returnButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void start(ActionEvent event) {
        
        
        
    }

    @FXML
    private void pause(ActionEvent event) {
        
        
        
    }

    @FXML
    private void reset(ActionEvent event) {
        
        
    }

    @FXML
    private void displaygraph(ActionEvent event) {
    }

    @FXML
    private void returnmenu(ActionEvent event) throws IOException {
            
        
    }
    
}
