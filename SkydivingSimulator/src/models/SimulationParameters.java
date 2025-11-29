/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/*
TODO BY: Aswinth

 */
/**
 *
 * @author Usman
 */
public class SimulationParameters {

    private double DragFactor;
    private double DC1;
    private double DC2;
    private double DC3;
    private double A1;
    private double A2;
    private double A3;
    private double deltaTime;
    private double mass;

    public SimulationParameters(double DragFactor, double deltaTime, double mass) {
        if (mass <= 0) {
            mass = 1;
        } else {
            this.mass = mass;
        }

        if (deltaTime <= 0) {
            deltaTime = 0.1;
        } else {
            this.deltaTime = deltaTime;

        }

        this.DragFactor = DragFactor;
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
}
