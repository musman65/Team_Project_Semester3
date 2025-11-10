package controllers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author 2472557
 */
public class SimulationController {

    private TableView tableview;

    private TableColumn<Integer, Double> time;
    private TableColumn<Integer, Double> height;
    private TableColumn<Integer, Double> velocity;
    private TableColumn<Integer, Double> acceleration;
    private TableColumn<Integer, Double> force;

    public TableView tableView() {
        tableview = new TableView<>();
        tableview.setPrefHeight(200);
        tableview.setPrefWidth(326);
        tableview.setLayoutX(427);
        tableview.setLayoutY(223);
        time = new TableColumn<>("Time");
        height = new TableColumn<>("Height");
        velocity = new TableColumn<>("Velocity");
        acceleration = new TableColumn<>("Acceleration");
        force = new TableColumn<>("Force");

        time.setSortable(false);
        height.setSortable(false);
        velocity.setSortable(false);
        acceleration.setSortable(false);
        force.setSortable(false);

        tableview.getColumns().add(time);
        tableview.getColumns().add(height);
        tableview.getColumns().add(velocity);
        tableview.getColumns().add(acceleration);
        tableview.getColumns().add(force);

        return tableview;

    }

}
