package assignment6.framework.movement;

import robocode.*; // for Double and Integer objects
import java.awt.geom.*; // for Point2D's

public class StopAndGoMovement extends AbstractMovement {

	/* default */ private static double direction = 1;
	static double lastEnemyHeading;
	public static double lastEnemyEnergy;
	public boolean flat;
	static boolean firstScan;
	static StringBuilder data = new StringBuilder();
	static double bulletVelocity;


	public StopAndGoMovement(AdvancedRobot robot) {
		super(robot);
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		double headingRadians;
		double eDistance;
		final double absbearing = event.getBearingRadians() + (headingRadians = robot.getHeadingRadians());
		final Point2D.Double myLocation = new Point2D.Double(robot.getX(), robot.getY());
		final boolean rammer = (eDistance = event.getDistance()) < 100 || robot.getTime() < 20;

		final Rectangle2D.Double field = new Rectangle2D.Double(17.9, 17.9, 764.1, 564.1);

		double v1, v2, offset = Math.PI / 2 + 1 - eDistance / 600;

		/* Wait loop */ while (!field.contains(project(myLocation, v2 = absbearing + direction * (offset -= 0.02), 160)));


		if ((flat && !rammer && Math.random() < 0.6 * Math.sqrt(bulletVelocity / eDistance) - 0.04)
			|| offset < Math.PI / 4) {
			direction = -direction;
		}
		
		robot.setTurnRightRadians(Math.tan(v2 -= headingRadians));

		//Test deltaE
		final double deltaE = setDelta(event);

		if ((0 < deltaE && deltaE < 3.001) || flat || rammer) {
			robot.setAhead((37 + ((int) (deltaE - 0.50001)) * 11) * Math.signum(Math.cos(v2)));
		}
	}

	public double setDelta(ScannedRobotEvent event){
		return lastEnemyEnergy - (lastEnemyEnergy = event.getEnergy());
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		lastEnemyEnergy += 20 - (bulletVelocity = event.getVelocity());
	}

	@Override
	public void onDeath(DeathEvent event) {
		if (robot.getRoundNum() < 3) {
			flat = true;	
		}
	}

	@Override
	public void onBulletHit(BulletHitEvent event) {
		lastEnemyEnergy -= 10;

	}

	/* default */
	private Point2D.Double project(Point2D.Double location, double angle, double distance) {
		return new Point2D.Double(location.x + distance * Math.sin(angle), location.y + distance * Math.cos(angle));
	}

}
