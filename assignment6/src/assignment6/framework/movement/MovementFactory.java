package assignment6.framework.movement;
import assignment6.framework.movement.*;
import robocode.AdvancedRobot;

public class MovementFactory {
    AdvancedRobot robot;
    public MovementFactory(AdvancedRobot robot){
        this.robot = robot;
    }
    public AbstractMovement getWaveSurfing(){
        return new WaveSurfingMovement(robot);
    }
    public AbstractMovement getRandomFluidOrbit(){
        return new RandomFluidOrbitMovement(robot);
    }
    public AbstractMovement getStopAndGo(){
        return new StopAndGoMovement(robot);
    }
    public AbstractMovement getNoneMovement(){
        return new NoneMovement(robot);
    }
}
