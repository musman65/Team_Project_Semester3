/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 6298674
 */
public class EulerMethodLogic {
    private Person diver;
    private double deltaTime;
    private double g;
    
    public EulerMethodLogic(Person diver, double deltaTime) {
        if (diver == null) {
            this.diver = new Person("John Doe", 75.0, 4000.0);
        } else {
            this.diver = diver;
        }
        if (deltaTime <= 0) {
            this.deltaTime = 0.1;
        } else {
          this.deltaTime = deltaTime;  
        }
        this.g = 9.8;
    }
    
    public Double gravitationalForce() {
        if (diver == null) {
            return null;
        }
        return (diver.getWeight() * g);
    }
    
//    public Double dragForce() {
//        
//    }
}
