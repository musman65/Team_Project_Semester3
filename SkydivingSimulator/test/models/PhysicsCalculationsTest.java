/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package models;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usman
 */
public class PhysicsCalculationsTest {
    
    private PhysicsCalculations physics;
    private Skydiver diver;
    private SimulationParameters params;

    public PhysicsCalculationsTest() {
        this.params  = new SimulationParameters(1, 0.1, 80.0);
        this.diver = new Skydiver(0.0, 0.0, -9.8, params);
        this.physics = new PhysicsCalculations(diver);
    }
    
    @Test
    public void testGetWeight() {
        double expected = 80.0 * 9.8;
        System.out.println(physics.getWeight());
        System.out.println(expected);
        assertEquals(expected, physics.getWeight(), 0.001);
    }

    @Test
    public void testGetDragForceParachute1() {
        double currentSpeed = 10.0;
        double drag = 0.5 * 1.225 * currentSpeed * currentSpeed * params.getDragFactor()
                      * params.getDC1() * params.getA1();
        assertEquals(drag, physics.getDragForce(1, currentSpeed), 0.001);
    }

    @Test
    public void testGetDragForceParachute2() {
        double currentSpeed = 12.0;
        double drag = 0.5 * 1.225 * currentSpeed * currentSpeed * params.getDragFactor()
                      * params.getDC2() * params.getA2();
        assertEquals(drag, physics.getDragForce(2, currentSpeed), 0.001);
    }

    @Test
    public void testGetDragForceParachute3() {
        double currentSpeed = 8.0;
        double drag = 0.5 * 1.225 * currentSpeed * currentSpeed * params.getDragFactor()
                      * params.getDC3() * params.getA3();
        assertEquals(drag, physics.getDragForce(3, currentSpeed), 0.001);
    }

    @Test
    public void testGetVelocity() {
        double vi = 5.0;
        double ai = 9.8;
        double expected = vi + ai * params.getDeltaTime(); // t = deltaTime
        assertEquals(expected, physics.getVelocity(vi, ai), 0.001);
    }

    @Test
    public void testGetPosition() {
        double yi = 0.0;
        double vi = 5.0;
        double ai = 9.8;
        double t = params.getDeltaTime();
        double expected = yi + vi*t + 0.5*ai*(t*t);
        assertEquals(expected, physics.getPostion(yi, vi, ai), 0.001);
    }

    @Test
    public void testGetNetForce() {
        double dragForce = 1000.0;
        double expected = dragForce - physics.getWeight();
        assertEquals(expected, physics.getNetForce(dragForce), 0.001);
    }

    @Test
    public void testGetAcceleration() {
        double netForce = 500.0;
        double expected = netForce / params.getMass(); // 80 kg
        assertEquals(expected, physics.getAcceleration(netForce), 0.001);
    }
}
