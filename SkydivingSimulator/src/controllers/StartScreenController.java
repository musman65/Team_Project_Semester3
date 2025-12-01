/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class for the application's start screen.
 * This screen allows the user to either enter the simulation
 * or exit the application. 
 * It serves as the first UI the user sees when launching the program.
 * 
 * @author 6321596
 */
public class StartScreenController implements Initializable {

    /**
     * This whole StartScreenController class is made by Sahel Assadi.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    /**
     * Event handler for the "Enter" button.
     * Loads the main simulation window (MainView.fxml)
     * and transitions the user from the start screen into the simulator.
     *
     * @param event the button click event
     * @throws IOException if the FXML file fails to load
     */
    @FXML
    private void enter(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));
        stage.getScene().setRoot(root);
       
    }

    /**
     * Event handler for the "Exit" button.
     * Closes the entire JavaFX application cleanly.
     *
     * @param event the button click event
     */
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

}
