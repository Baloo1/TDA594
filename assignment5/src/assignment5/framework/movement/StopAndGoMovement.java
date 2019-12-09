package assignment5.framework.movement;

import robocode.*; // for Double and Integer objects
import java.awt.geom.*; // for Point2D's

public class StopAndGoMovement extends AbstractMovement {

	/* default */ static double direction = 1;
	/* default */ final static double angleScale = 24;
	/* default */ final static double velocityScale = 1;
	static double lastEnemyHeading;
	static double lastEnemyEnergy;
	boolean flat;
	static boolean firstScan;
	static StringBuilder data = new StringBuilder();
	static double bulletVelocity;

	public StopAndGoMovement(AdvancedRobot robot) {
		super(robot);
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		double headingRadians;
		double eDistance;
		// double eHeadingRadians;
		double absbearing = e.getBearingRadians() + (headingRadians = robot.getHeadingRadians());
		Point2D.Double myLocation = new Point2D.Double(robot.getX(), robot.getY());
		boolean rammer = (eDistance = e.getDistance()) < 100 || robot.getTime() < 20;

		Rectangle2D.Double field = new Rectangle2D.Double(17.9, 17.9, 764.1, 564.1);

		// movement \/ \/
		double v1, v2, offset = Math.PI / 2 + 1 - eDistance / 600;

		while (!field.contains(project(myLocation, v2 = absbearing + direction * (offset -= 0.02), 160))
		// contains(getX() + 160 * Math.sin(v2 = absbearing + direction * (offset -=
		// .02)), getY() + 160 * Math.cos(v2))
		)
			;

		if ((flat && !rammer &&
		// Raiko's flattener
		// (Math.random() > Math.pow(v1 = 0.5952 * bulletVelocity /eDistance, v1))

		// a sbf style flattener - seems like it's easy to hit for FloodMini
		// getTime() >= lastTurnTime + eDistance/bulletVelocity*random

		// My flattener
				Math.random() < 0.6 * Math.sqrt(bulletVelocity / eDistance) - 0.04

		// Thorn's flattener - FloodGrapher shows it's flatter but it gives worse
		// results!??!?
		// Math.random() < 0.65*Math.pow(eDistance/bulletVelocity, -0.65)

		// a fairly good linear approximation of Raiko and Thorn
		// Math.random() < 1.1*bulletVelocity/eDistance + 0.03

		// Aristocles' flattener - light on codesize but easy to hit when the distance
		// isn't 300-400
		// Math.random() < 2.374873835*bulletVelocity/eDistance

		) || offset < Math.PI / 4) {
			direction = -direction;
			// lastTurnTime = getTime();
			// random = Math.random();
		}
		robot.setTurnRightRadians(Math.tan(v2 -= headingRadians));

		double deltaE = (lastEnemyEnergy - (lastEnemyEnergy = e.getEnergy()));

		if ((0 < deltaE && deltaE < 3.001) || flat || rammer) {
			robot.setAhead((37 + ((int) (deltaE - 0.50001)) * 11) * Math.signum(Math.cos(v2)));
		}

		// movement /\ /\

	}

	@Override
	public void onHitByBullet(HitByBulletEvent e) {

		lastEnemyEnergy += 20 - (bulletVelocity = e.getVelocity());

		// if(hits++ > 6 && getRoundNum() < 5 )
		// flat = true;
	}

	@Override
	public void onDeath(DeathEvent e) {

		if (robot.getRoundNum() < 3)
			flat = true;

	}

	@Override
	public void onBulletHit(BulletHitEvent e) {
		// double bp = e.getBullet().getPower();
		// lastEnemyEnergy -= Math.max( bp*4 +,bp*6 - 2);
		lastEnemyEnergy -= 10;

	}

	Point2D.Double project(Point2D.Double location, double angle, double distance) {
		return new Point2D.Double(location.x + distance * Math.sin(angle), location.y + distance * Math.cos(angle));
	}

}
