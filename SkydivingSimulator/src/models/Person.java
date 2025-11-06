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
    private double weight; // The weight of the person
    private double projectedArea = 0.6; // [m^2] Surface area of the side that is facing air flow, using 0.6 as an average because it would be too hard to calculat it for everyone
    
    public Person(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
    
    public String getName() {
        return name;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setName(String name) {
       this.name = name;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
