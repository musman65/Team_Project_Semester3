/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/*
TODO BY: Usman
Runs the Euler-method physics simulation,
updating the Skydiver’s state step-by-step using
the parameters provided.
*/

/**
 * SimulationEngine performs the step-by-step simulation of a skydiver's
 * fall using the Euler method and the provided physics calculations.
 * It tracks the skydiver's position, velocity, acceleration, and net force
 * over time, while managing parachute deployment stages.
 * 
 * @author Usman
 */
public class SimulationEngine {
    // The Skydiver being simulated
    private Skydiver diver;
    // PhysicsCalculations object used to compute forces, acceleration, and motion
    private PhysicsCalculations pc;
    // Array storing current state: [0] position, [1] velocity, [2] acceleration, [3] net force
    private double[] initialCondition = new double[4];
    
    // Time related fields
    private double timeframe;
    // Time when the small parachute deploys (seconds)
    private double deployementOfSmallParachute = 30;
    // Time when the big parachute deploys (seconds)
    private double deployementOfBigParachute = 35;
    
    /**
     * Constructs a SimulationEngine for a given skydiver.
     *
     * @param diver the Skydiver object to simulate
     */
    public SimulationEngine(Skydiver diver) {
        if (diver == null) {
            
        } else {
            this.diver = diver;
        }
        pc = new PhysicsCalculations(this.diver);
        
        // Initialize initial conditions
        initialCondition[0] = diver.getCurrentPosition(); // 0 :Postion
        initialCondition[1] = diver.getCurrentVelocity(); // 1 : Velocity
        initialCondition[2] = diver.getCurrentAcceleration(); // 2 : Acceleration
        initialCondition[3] = pc.getNetForce(pc.getDragForce(1, diver.getCurrentVelocity())); // 3 : Net Force
        
        // Initialize simulation time
        timeframe = diver.getParams().getDeltaTime();
        
    }
    
    /**
     * Computes the next state of the skydiver for one simulation step.
     * This method uses the Euler method to update position, velocity,
     * acceleration, and net force based on the current state and
     * parachute deployment stage.
     *
     * @return an array of doubles containing:
     *         [0] position (m), [1] velocity (m/s),
     *         [2] acceleration (m/s²), [3] net force (N)
     */
    public double[] computationOfOneRow() {
        
        // Compute next position and velocity using Euler method
        double currentPosition = pc.getPostion(initialCondition[0], initialCondition[1], initialCondition[2]);
        double currentVelocity = pc.getVelocity(initialCondition[1], initialCondition[2]);
        
        // Determine parachute stage
        int orderOfParachute = 1;
        
        if (timeframe >= deployementOfSmallParachute && timeframe < deployementOfBigParachute) { // DEPLOYS SMALL PARACHUTE
            orderOfParachute = 2;
        } else if (timeframe >= deployementOfBigParachute) { // DEPLOYS BIG PARACHUTE
            orderOfParachute = 3;
            diver.getParams().updateA3();
        } else if (timeframe < deployementOfSmallParachute) { // FREEFALL
            orderOfParachute = 1;
        }
        
        // Calculate forces
        double currentDragForce = pc.getDragForce(orderOfParachute, currentVelocity);
        double currentNetForce = pc.getNetForce(currentDragForce);
        double currentAcceleration = pc.getAcceleration(currentNetForce);

        // Round values for display
        currentPosition = Double.parseDouble(String.format("%.2f", currentPosition)); 
        currentVelocity = Double.parseDouble(String.format("%.2f", currentVelocity)); 
//        currentAcceleration = Double.parseDouble(String.format("%.2f", currentAcceleration)); 
        currentNetForce = Double.parseDouble(String.format("%.2f", currentNetForce)); 
        
        // Update state
        double [] newConditions = {currentPosition, currentVelocity, currentAcceleration, currentNetForce};
        initialCondition = newConditions;
        
        // Increment simulation time
        timeframe += diver.getParams().getDeltaTime();
        timeframe = Double.parseDouble(String.format("%.3f", timeframe)); 
        return newConditions;
    } 

    /**
     * Returns the current simulation time.
     *
     * @return the current timeframe in seconds
     */
    public double getTimeframe() {
        return timeframe;
    }    
}
