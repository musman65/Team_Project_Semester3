/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


/**
 * SimulationResults holds the data for a single time step of the skydiving simulation.
 * Each instance represents the state of the skydiver at a specific point in time.
 * 
 * @author Usman
 */
public class SimulationResults {
    // Time of the simulation step (seconds)
    private double time;
    // Height of the skydiver above ground (meters) 
    private double position;
    // Velocity of the skydiver (m/s)
    private double velocity;
    // Acceleration of the skydiver (m/s^2)
    private double acceleration;
    //  Net force acting on the skydiver (Newtons)
    private double force;

    /**
     * Constructs a SimulationResults object with all state parameters.
     * 
     * @param time time of the simulation step (s)
     * @param position current height above ground (m)
     * @param velocity current velocity (m/s)
     * @param acceleration current acceleration (m/s²)
     * @param force net force on the skydiver (N)
     */
    public SimulationResults(double time, double position, double velocity, double acceleration, double force) {
        this.time = time;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.force = force;
    }

    public double getTime() {
        return time;
    }

    public double getPosition() {
        return position;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getForce() {
        return force;
    }
}
