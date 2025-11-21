package models;

/*
TODO BY: Usman
Contains the methods used to calculate the forces
by inputting the parameters such as mass, etc 
using the Euler Method.
*/

/**
 *
 * @author Usman
 */
public class PhysicsCalculations {
    private Skydiver diver;
    private SimulationParameters params;
    private double t;
    private double m;
    
    public PhysicsCalculations(Skydiver diver) {
        if (diver  == null) {
            //TODO
        }
        this.diver = diver;
        this.params = diver.getParams();
        t = params.getDeltaTime();
        m = params.getMass();
    }
    
    // Inputs should have already been validated before reaching these methods
    
    public double getWeight() {
        return (m * 9.8);
    }
    
    public double getSpeed(double vi, double a) {
        return (vi + a * t);
    }
    
    public double getVerticalHeight(double yi, double vi, double a) {
        return (yi + vi*t + 0.5*a*(t*t));
    }
}
