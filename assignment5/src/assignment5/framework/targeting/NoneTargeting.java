package assignment5.framework.targeting;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class NoneTargeting extends AbstractTargeting {
	/**
	 * @param robot an instance of a RoboCode robot
	 */
	public NoneTargeting(AdvancedRobot robot) {
		super(robot);
	}

	/**
	 * Not implemented, not needed
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		// TODO Auto-generated method stub

	}
}
