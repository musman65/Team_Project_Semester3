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
        } else {        
            this.diver = diver;
        }
        this.params = diver.getParams();
        t = params.getDeltaTime();
        m = params.getMass();
    }
    
    // Inputs should have already been validated before reaching these methods
    
    public double getWeight() {
        return (m * 9.8);
    }
    
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
        return 0.0;
    }
    
    public double getVelocity(double vi, double ai) {
        return (vi + ai * t);
    }
    
    public double getPostion(double yi, double vi, double ai) {
        return (yi + vi*t + 0.5*ai*(t*t));
    }
    
    public double getNetForce(double currentDragForce) {
        return (currentDragForce - this.getWeight());
    }
    
    public double getAcceleration(double netForce) {
        return (netForce / m);
    }
    
}
