package assignment6.framework.targeting;
import assignment6.framework.targeting.AbstractTargeting;
import assignment6.framework.targeting.GFTargeting;
import assignment6.framework.targeting.LinearTargeting;
import assignment6.framework.targeting.NoneTargeting;
import robocode.AdvancedRobot;


public class TargetingFactory {
    AdvancedRobot robot;
    public TargetingFactory(AdvancedRobot robot){
        this.robot = robot;
    }
    public AbstractTargeting getGFTargeting(){
        return new GFTargeting(robot);
    }
    public AbstractTargeting getLinearTargeting(){
        return new LinearTargeting(robot);
    }
    public AbstractTargeting getNoneTargeting(){
        return new NoneTargeting(robot);
    }


}
