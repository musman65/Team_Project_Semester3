/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * SimulationParameters holds all configurable parameters for a skydiving
 * simulation, including drag factors, time step, mass, and parachute 
 * deployment coefficients.
 * This class provides getters for the parameters and a method to gradually
 * update the big parachute deployment coefficient (A3) over time.
 * 
 * @author Aswinth
 */
public class SimulationParameters {

    // Multiplier for overall drag applied to the diver
    private double DragFactor;
    
    // Drag coefficients for different parachute stages
    private double DC1 = 0.5; // Freefall
    private double DC2 = 1.25; // Small parachute
    private double DC3 = 1.75; // Big parachute
    
    // Area coefficients for drag calculation 
    private double A1 = 1.91; // Freefall
    private double A2 = 1.5; // Small parachute
    private double A3Start = 1.5; // Big parachute initial area
    private double A3 = A3Start; // Big parachute current area
    private double A3End = 13; // Big parachute fully deployed
    
    // Time in seconds over which the big parachute fully deploys
    private double fullCanopyDeploymentTime = 5.0;
    
    // Step increment for gradual A3 increase per simulation frame
    private double A3Step = A3End / (fullCanopyDeploymentTime * 10) ;
    
    // Time step (delta time) used in Euler simulation
    private double deltaTime;
    
    // Mass of the skydiver (kg) 
    private double mass;

    /**
     * Constructs SimulationParameters with user-defined drag factor, time step, and mass.
     * Performs basic validation on mass and deltaTime to ensure reasonable defaults.
     * 
     * @param DragFactor multiplier for drag forces
     * @param deltaTime time step for simulation (seconds)
     * @param mass mass of the skydiver (kg)
     */
    public SimulationParameters(double DragFactor, double deltaTime, double mass) {
        if (mass <= 0) {
            mass = 1; // default to 1 kg if invalid
        } else {
            this.mass = mass;
        }

        if (deltaTime <= 0 || deltaTime >= 0.5) {
            deltaTime = 0.1; // default to 0.1 s if invalid
        } else {
            this.deltaTime = deltaTime;
        }

        this.DragFactor = DragFactor;
    }
    
    /**
     * Gradually updates the big parachute area coefficient (A3)
     * until it reaches its maximum value (A3End).
     * This is called each simulation frame during big parachute deployment.
     */
    public void updateA3() {
        if (!(A3 >= A3End)) {            
            this.A3 += A3Step;
        }
    }
    
    public double getDragFactor() {
        return DragFactor;
    }

    public double getDC1() {
        return DC1;
    }

    public double getDC2() {
        return DC2;
    }

    public double getDC3() {
        return DC3;
    }

    public double getA1() {
        return A1;
    }

    public double getA2() {
        return A2;
    }

    public double getA3() {
        return A3;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public double getMass() {
        return mass;
    }
    
    /**
     * Returns a readable string describing key simulation parameters.
     *
     * @return string containing mass, deltaTime, and drag factor
     */
    @Override
    public String toString() {
        return "Mass: " + mass + ", Delta Time: " + deltaTime + ", Drag Factor: " + DragFactor;
    }
}
