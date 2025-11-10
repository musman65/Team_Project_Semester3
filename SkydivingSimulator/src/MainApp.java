/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import controllers.SimulationController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**Stage
 *
 * @author sahel
 */
public class MainApp extends Application {

    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }

    @Override
    public void start(Stage stage) throws IOException {
//      Parent root = FXMLLoader.load(getClass().getResource("/views/file.fxml"));



        SimulationController sc = new SimulationController();
        
       
        stage.setTitle("Skydiving/Parachute Simulator");
        Scene scene = new Scene(sc.tableView(), 757, 421);
        stage.setScene(scene);
        stage.show();
    }
}
