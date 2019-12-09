package assignment5.framework.targeting;

import assignment5.framework.IEventsTarget;
import robocode.*;

public abstract class AbstractTargeting implements IEventsTarget {
	
	protected AdvancedRobot robot;
	
	public AbstractTargeting(AdvancedRobot robot) {
		this.robot = robot;		
	}
	
	@Override
	public abstract void onScannedRobot(ScannedRobotEvent e);
}
