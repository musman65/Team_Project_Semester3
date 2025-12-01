package models;

/*
TODO BY: Usman
Contains the methods used to calculate the forces
by inputting the parameters such as mass, etc 
using the Euler Method.
*/

/**
 * Handles the physics calculations for the skydiving simulation.
 * This class performs all computations related to skydiver motion, including
 * weight, drag force, net force, acceleration, and velocity/position updates
 * using the Euler Method. It relies on Skydiver and SimulationParameters 
 * to provide required input values such as mass, drag coefficients, areas, and time steps.
 * @author Usman
 */
public class PhysicsCalculations {
    // The Skydiver object containing state values and parameters.
    private Skydiver diver;
    // Simulation parameters containing drag factors, mass, and time step.
    private SimulationParameters params;
    // Time step (Δt) used for Euler method calculations.
    private double t;
    // Mass (kg) of the skydiver.
    private double m;
    
    /**
     * Constructs a PhysicsCalculations object using the provided Skydiver.
     *
     * @param diver the skydiver whose physical properties are used
     */
    public PhysicsCalculations(Skydiver diver) {
        if (diver  == null) {
            diver = new Skydiver(4000, 0, -9.8, new SimulationParameters(1, 0.1, 75)); // Default skydiver
        } else {        
            this.diver = diver;
        }
        this.params = diver.getParams();
        t = params.getDeltaTime();
        m = params.getMass();
    }
    
    /**
     * Calculates the gravitational weight force (mg) acting on the skydiver.
     *
     * @return the weight force in Newtons
     */
    public double getWeight() {
        return (m * 9.8);
    }
    
    /**
     * Calculates the drag force acting on the skydiver based on the selected
     * parachute stage and current speed.
     * Uses the drag equation: Fd = 0.5 * ρ * v^2 * Cd * A
     * where ρ = air density (1.225 kg/m^3).
     *
     * @param parachuteOrder the parachute stage (1, 2, or 3)
     * @param currentSpeed   the current velocity of the skydiver (m/s)
     * @return the drag force in Newtons
     */
    public double getDragForce(int parachuteOrder, double currentSpeed) {
        double drag = 0.5 * 1.225 * currentSpeed * currentSpeed * params.getDragFactor();
        switch (parachuteOrder) {
            case 1 -> {
                return drag * params.getDC1() * params.getA1();
            }
            case 2 -> {
                return drag * params.getDC2() * params.getA2();
            }
            case 3 -> {
                return drag * params.getDC3() * params.getA3();
            }
        }
        // No parachute stage selected
        return 0.0;
    }
    
    /**
     * Computes the updated velocity using the Euler Method:
     * v = vi + ai * Δt
     *
     * @param vi initial velocity
     * @param ai current acceleration
     * @return the updated velocity
     */
    public double getVelocity(double vi, double ai) {
        return (vi + ai * t);
    }
    
    /**
     * Computes the updated position using the kinematic equation:
     * y = yi + viΔt + 0.5aΔt^2
     *
     * @param yi initial position
     * @param vi initial velocity
     * @param ai current acceleration
     * @return the updated position
     */
    public double getPostion(double yi, double vi, double ai) {
        return (yi + vi*t + 0.5*ai*(t*t));
    }
    
    /**
     * Calculates the net force acting on the skydiver:
     * Fnet = Fdrag - Fweight
     * (Drag is upward, weight is downward)
     *
     * @param currentDragForce the drag force acting upward
     * @return the net force
     */
    public double getNetForce(double currentDragForce) {
        return (currentDragForce - this.getWeight());
    }
    
    /**
     * Calculates the acceleration of the skydiver using Newton's Second Law:
     * a = F / m
     *
     * @param netForce the net force applied to the skydiver
     * @return the calculated acceleration
     */
    public double getAcceleration(double netForce) {
        return (netForce / m);
    }
    
}
