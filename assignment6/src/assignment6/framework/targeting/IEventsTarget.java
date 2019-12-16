package assignment6.framework.targeting;

import robocode.*;

public interface IEventsTarget {
	/**
	 * @param event triggered event when something happens in RoboCode
	 */
	void onScannedRobot(ScannedRobotEvent event);
}
