/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/*
TODO BY: Usman

*/

/**
 *
 * @author Usman
 */
public class SimulationResults {
    private double time;
    private double position;
    private double velocity;
    private double acceleration;
    private double force;

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
