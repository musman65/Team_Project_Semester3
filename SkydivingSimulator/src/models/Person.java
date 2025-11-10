/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Usman
 */
public class Person {
    private String name;
    private double height; // How high up the diver jumps from? [m]
    private Double weight; // The weight of the person [kg]
    private Double projectedArea = 0.6; // [m^2] Surface area of the side that is facing air flow, using 0.6 as an average because it would be impossible to calculate it for everyone due to there being more factors than just mass, height etc.
    
    public Person(String name, Double weight, Double height) {
        this.name = name;
        this.weight = weight;
        this.height = height;
    }
    
    public String getName() {
        return name;
    }
    
    public Double getWeight() {
        return weight;
    }
    
    public Double getHeight() {
        return height;
    }
    
    public void setName(String name) {
       this.name = name;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public void setHeight(Double height) {
        this.height = height;
    }
}
