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
 *
 * @author Usman
 */
public class SimulationEngine {
    private Skydiver diver;
    private PhysicsCalculations pc;
    private double[] initialCondition = new double[4];
    
    // Time related fields
    private double timeframe;
    private double deployementOfSmallParachute = 30; // TBD
    private double deployementOfBigParachute = 32; // TBD
    
    public SimulationEngine(Skydiver diver) {
        if (diver == null) {
            //TODO
        } else {
            this.diver = diver;
        }
        pc = new PhysicsCalculations(this.diver);
        initialCondition[0] = diver.getCurrentPosition(); // 0 :Postion
        initialCondition[1] = diver.getCurrentVelocity(); // 1 : Velocity
        initialCondition[2] = diver.getCurrentAcceleration(); // 2 : Acceleration
        initialCondition[3] = pc.getNetForce(pc.getDragForce(1, diver.getCurrentVelocity())); // 3 : Net Force
        
        timeframe = diver.getParams().getDeltaTime();
        
    }
    
    public double[] computationOfOneRow() {
        double currentPosition = pc.getPostion(initialCondition[0], initialCondition[1], initialCondition[2]);
        double currentVelocity = pc.getVelocity(initialCondition[1], initialCondition[2]);
        int orderOfParachute = 1;
        
        if (timeframe >= deployementOfSmallParachute && timeframe < deployementOfBigParachute) { // DEPLOYS SMALL PARACHUTE
            orderOfParachute = 1;
        } else if (timeframe >= deployementOfBigParachute) { // DEPLOS BIG PARACHUTE
            orderOfParachute = 2;
        } else if (timeframe < deployementOfSmallParachute) { // FREEFALL
            orderOfParachute = 3;
        }
        
        double currentDragForce = pc.getDragForce(orderOfParachute, currentVelocity);
        System.out.println(currentDragForce);
        double currentNetForce = pc.getNetForce(currentDragForce);
        double currentAcceleration = pc.getAcceleration(currentNetForce);
        
        currentPosition = Double.parseDouble(String.format("%.2f", currentPosition)); 
        currentVelocity = Double.parseDouble(String.format("%.2f", currentVelocity)); 
        currentAcceleration = Double.parseDouble(String.format("%.2f", currentAcceleration)); 
        currentNetForce = Double.parseDouble(String.format("%.2f", currentNetForce)); 
        
        double [] newConditions = {currentPosition, currentVelocity, currentAcceleration, currentNetForce};
        initialCondition = newConditions;
        timeframe += diver.getParams().getDeltaTime();
        timeframe = Double.parseDouble(String.format("%.1f", timeframe)); 
        return newConditions;
    } 

    public double getTimeframe() {
        return timeframe;
    }
    
}
