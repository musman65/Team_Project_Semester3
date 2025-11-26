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

    private SimulationParameters Params = new SimulationParameters();
    private double CurrentPosition;
    private double CurrentVelocity;
    private double CurrentAcceleration;
    private double CurrentNetForce;
    
    public void Skydiver(double CurrentPosition, double CurrentVelocity, double CurrentAcceleration, double CurrentNetForce, SimulationParameters Params) {
        this.CurrentPosition = CurrentPosition;
        this.CurrentAcceleration = CurrentAcceleration;
        this.CurrentNetForce = CurrentNetForce;
        this.CurrentVelocity = CurrentVelocity;
        this.Params = Params;
        
    }

    public SimulationParameters getParams() {
        return Params;
    }

    public double getCurrentPosition() {
        return 0.0;
    }

    public double getCurrentVelocity() {
        return 0.0;
    }

    public double getCurrentAcceleration() {
        return 0.0;
    }

    public double getCurrentNetForce() {
        return 0.0;
    }

}
