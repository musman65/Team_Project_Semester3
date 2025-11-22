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
    private double timeframe = diver.getParams().getDeltaTime();
    
    public SimulationEngine(Skydiver diver) {
        if (diver == null) {
            //TODO
        } else {
            this.diver = diver;
        }
        pc = new PhysicsCalculations(this.diver);
        initialCondition[0] = diver.getCurrentPosition(); // 0 :Postion, 1 : Velocity, 2 : Acceleration, 3 : Net Force
        initialCondition[1] = diver.getCurrentSpeed(); 
        initialCondition[2] = diver.getCurrentAcceleration(); 
        initialCondition[3] = diver.getCurrentNetForce(); 
    }
    
    public double[] computationOfOneRow() {
        
        
        return null;
    } 
    
}
