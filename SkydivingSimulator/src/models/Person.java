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
    private int weight; // The weight of the person
    private double projectedArea = 0.6; // [m^2] Surface area of the side that is facing air flow, using 0.6 as an average because it would be too hard to calculat it for everyone
    
    public Person(int height, int weight) {
        this.weight = weight;
    }
}
