package assignment6.framework.targeting;

import robocode.*;
import robocode.util.*;
import java.awt.geom.*;

public class LinearTargeting extends AbstractTargeting {
	/**
	 * @param robot an instance of a RoboCode robot
	 */
	public LinearTargeting(AdvancedRobot robot) {
		super(robot);
	}

	/**
	 * @param event triggered event when something happens in RoboCode
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		final double bulletPower = Math.min(3.0, robot.getEnergy());
		final double myX = robot.getX();
		final double myY = robot.getY();
		final double absoluteBearing = robot.getHeadingRadians() + event.getBearingRadians();
		final double enemyX = robot.getX() + event.getDistance() * Math.sin(absoluteBearing);
		final double enemyY = robot.getY() + event.getDistance() * Math.cos(absoluteBearing);
		final double enemyHeading = event.getHeadingRadians();
		final double enemyVelocity = event.getVelocity();
		double deltaTime = 1;

		final double battleFieldHeight = robot.getBattleFieldHeight();
		final double battleFieldWidth = robot.getBattleFieldWidth();
		double predictedX = enemyX;
		double predictedY = enemyY;
		while (deltaTime * (20.0 - 3.0 * bulletPower) < Point2D.Double.distance(myX, myY, predictedX, predictedY)) {
			predictedX += Math.sin(enemyHeading) * enemyVelocity;
			predictedY += Math.cos(enemyHeading) * enemyVelocity;
			if (predictedX < 18.0 || predictedY < 18.0 || predictedX > battleFieldWidth - 18.0
					|| predictedY > battleFieldHeight - 18.0) {
				predictedX = Math.min(Math.max(18.0, predictedX), battleFieldWidth - 18.0);
				predictedY = Math.min(Math.max(18.0, predictedY), battleFieldHeight - 18.0);
				break;
			}
			deltaTime++;
		}
		final double theta = Utils
				.normalAbsoluteAngle(Math.atan2(predictedX - robot.getX(), predictedY - robot.getY()));
		robot.setTurnRadarRightRadians(Utils.normalRelativeAngle(absoluteBearing - robot.getRadarHeadingRadians()));
		robot.setTurnGunRightRadians(Utils.normalRelativeAngle(theta - robot.getGunHeadingRadians()));
		robot.fire(bulletPower);
	}

}
