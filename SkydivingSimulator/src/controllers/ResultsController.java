package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for the results screen shown after the simulation finishes.
 * This class displays:
 * Two images (for visual design.
 * The total time the skydiver took to reach the ground.
 * 
 * The SimulationController passes the total landing time to this controller,
 * and the label is updated when the screen loads.
 */
public class ResultsController {
    // The landing time passed in from the simulation controller.
    double timeTakenDouble = 0;
    
    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private Label timeTaken;
    
    /**
     * Called automatically after the FXML file has been loaded.
     * This method:
     * Loads two images into the ImageViews.
     * Formats and displays the total simulation time.
     */
    @FXML
    public void initialize() {
        
        // Load images stored in the resources folder
        imageView1.setImage(new Image("/views/images/image1.jpg"));
        imageView2.setImage(new Image("/views/images/image2.jpg"));
        
        // Update the landing time label once the UI thread is ready
        Platform.runLater(() -> {
           // format time to 3 decimals
           String s = String.format("%.3f", timeTakenDouble);
            timeTaken.setText("Time taken to land: " + s); 
        });
    }
    
    /**
     * Receives the total landing time from the simulation.
     * This method is called BEFORE the Results screen is shown,
     * allowing the initialize() method to display the correct value.
     * @param time the total time (seconds) the skydiver took to reach the ground
     */
    public void setTimeTaken(double time) {
        timeTakenDouble = time;
    }

}
