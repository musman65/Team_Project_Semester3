package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResultsController {
    double timeTakenDouble = 0;
    
    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private Label timeTaken;
    
    @FXML
    public void initialize() {
        imageView1.setImage(new Image("/views/images/image1.jpg"));
        imageView2.setImage(new Image("/views/images/image2.jpg"));
        Platform.runLater(() -> {
           String s = String.format("%.3f", timeTakenDouble);
            timeTaken.setText("Time taken to land: " + s); 
        });
    }
    
    public void setTimeTaken(double time) {
        timeTakenDouble = time;
    }

}
