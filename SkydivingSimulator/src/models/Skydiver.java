/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


/**
 * Represents a skydiver in the simulation.
 * Holds the current state of the skydiver including position, velocity, acceleration, and net force.
 * Also stores simulation parameters like mass, drag factor, and time step.
 * 
 * @author Aswinth
 */
public class Skydiver {

    // Simulation parameters associated with this skydiver (mass, drag factor, etc.) 
    private SimulationParameters Params;
    // Current height of the skydiver above ground (meters)
    private double currentPosition;
    // Current velocity of the skydiver (m/s) 
    private double currentVelocity;
    // Current acceleration of the skydiver (m/s^2) 
    private double currentAcceleration;
    // Current net force acting on the skydiver (Newtons)
    private double currentNetForce;
    
     /**
     * Constructs a Skydiver object with initial conditions and simulation parameters.
     * 
     * @param CurrentPosition initial height above the ground (m)
     * @param CurrentVelocity initial velocity (m/s)
     * @param CurrentAcceleration initial acceleration (m/s²)
     * @param Params simulation parameters (mass, drag factor, deltaTime)
     */
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
