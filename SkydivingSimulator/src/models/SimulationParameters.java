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

    public void SimulationParamters(double DragFactor, double DC1, double DC2, double DC3, double A1,
            double A2, double A3, double deltaTime, double mass) {
        if (mass <= 0) {
            mass = 1;
        } else {
            this.mass = 0;
        }

        if (deltaTime <= 0) {
            deltaTime = 0.1;
        } else {
            this.deltaTime = 0;
        }

        if (DC1 <= 0) {
            DC1 = 1;
        } else {
            this.DC1 = 0;
        }

        if (DC2 <= 0) {
            DC2 = 1;
        } else {
            this.DC2 = 0;
        }

        if (DC3 <= 0) {
            DC3 = 1;
        } else {
            this.DC3 = 0;
        }

        if (A1 <= 0) {
            A1 = 1;
        } else {
            this.A1 = 0;
        }

        if (A2 <= 0) {
            A2 = 1;
        } else {
            this.A2 = 0;
        }

        if (A3 <= 0) {
            A3 = 1;
        } else {
            this.A3 = 0;
        }

        this.DragFactor = DragFactor;
        this.DC1 = DC1;
        this.DC2 = DC2;
        this.DC3 = DC3;
        this.A1 = A1;
        this.A2 = A2;
        this.A3 = A3;
        this.deltaTime = deltaTime;
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
