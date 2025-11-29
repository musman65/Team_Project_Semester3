/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/*
TODO BY: Aswinth

Represents the physical state of the skydiver,
storing dynamic variables like current height,
velocity, acceleration, and methods to update them.
 */
/**
 *
 * @author Usman
 */
public class Skydiver {

    private SimulationParameters Params;
    private double currentPosition;
    private double currentVelocity;
    private double currentAcceleration;
    private double currentNetForce;

    public Skydiver(double CurrentPosition, double CurrentVelocity, double CurrentAcceleration, SimulationParameters Params) {
        this.currentPosition = CurrentPosition;
        this.currentAcceleration = CurrentAcceleration;
        this.currentVelocity = CurrentVelocity;
        this.Params = Params;
    }

    public SimulationParameters getParams() {
        return Params;
    }

    public double getCurrentPosition() {
        return currentPosition;
    }

    public double getCurrentVelocity() {
        return currentVelocity;
    }

    public double getCurrentAcceleration() {
        return currentAcceleration;
    }

    public double getCurrentNetForce() {
        return currentNetForce;
    }

}
