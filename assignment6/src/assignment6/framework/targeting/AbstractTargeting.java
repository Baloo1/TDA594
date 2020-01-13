package assignment6.framework.targeting;

import robocode.*;

public abstract class AbstractTargeting implements IEventsTarget {
	
	protected AdvancedRobot robot;
	
	public AbstractTargeting(AdvancedRobot robot) {
	    this.robot = robot;
	}

	@Override
	public abstract void onScannedRobot(ScannedRobotEvent event);
}
