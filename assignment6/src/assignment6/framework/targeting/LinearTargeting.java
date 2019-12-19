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
		final double bulletPower = getBulletPower(robot.getEnergy());
		final double absoluteBearing = getAbsoluteBearing(robot, event);

		double predictedX = getEnemyX(robot, event, absoluteBearing);
		double predictedY = getEnemyY(robot, event, absoluteBearing);

		final double battleFieldHeight = robot.getBattleFieldHeight();
		final double battleFieldWidth = robot.getBattleFieldWidth();

		double deltaTime = 1;
		while (deltaTime * (20.0 - 3.0 * bulletPower) < Point2D.Double.distance(robot.getX(), robot.getY(), predictedX, predictedY)) {
			predictedX += Math.sin(event.getHeadingRadians()) * event.getVelocity();
			predictedY += Math.cos(event.getHeadingRadians()) * event.getVelocity();
			if (predictedX < 18.0 || predictedY < 18.0 || predictedX > battleFieldWidth - 18.0
					|| predictedY > battleFieldHeight - 18.0) {
				predictedX = Math.min(Math.max(18.0, predictedX), battleFieldWidth - 18.0);
				predictedY = Math.min(Math.max(18.0, predictedY), battleFieldHeight - 18.0);
				break;
			}
			deltaTime++;
		}

		final double theta = getTheta(predictedX, predictedY);

		robot.setTurnRadarRightRadians(Utils.normalRelativeAngle(absoluteBearing - robot.getRadarHeadingRadians()));
		robot.setTurnGunRightRadians(Utils.normalRelativeAngle(theta - robot.getGunHeadingRadians()));
		robot.fire(bulletPower);
	}

	public double getEnemyY(AdvancedRobot robot, ScannedRobotEvent event, double absoluteBearing) {
		return robot.getY() + event.getDistance() * Math.cos(absoluteBearing);
	}

	public double getEnemyX(AdvancedRobot robot, ScannedRobotEvent event, double absoluteBearing) {
		return robot.getX() + event.getDistance() * Math.sin(absoluteBearing);
	}

	public double getAbsoluteBearing(AdvancedRobot robot, ScannedRobotEvent event) {
		return robot.getHeadingRadians() + event.getBearingRadians();
	}

	public double getBulletPower(double energy) {
		return Math.min(3.0, energy);
	}

	public double getTheta(double predictedX, double predictedY) {
		return Utils.normalAbsoluteAngle(Math.atan2(predictedX - robot.getX(), predictedY - robot.getY()));
	}

}
