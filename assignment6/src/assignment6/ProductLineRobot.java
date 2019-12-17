package assignment6;

import robocode.*;
import assignment6.framework.targeting.*;
import assignment6.config.ConfigurationManager;
import assignment6.framework.movement.*;
 

public class ProductLineRobot extends AdvancedRobot implements IEventsTarget, IEventsMovement {
	private AbstractMovement movement;
	private AbstractTargeting targeting;
	private MovementFactory movementFactory;
	private TargetingFactory targetingFactory;
	
	@Override
	public void run() {
		targetingFactory = new TargetingFactory(this);
		movementFactory = new MovementFactory(this);
		if (ConfigurationManager.getInstance().getProperty("WaveSurfing")) {
			movement = movementFactory.getWaveSurfing();
		} else if (ConfigurationManager.getInstance().getProperty("RandomFluidOrbit")) {
			movement = movementFactory.getRandomFluidOrbit();
		} else if (ConfigurationManager.getInstance().getProperty("StopAndGo")) {
			movement = movementFactory.getStopAndGo();
		} else if (ConfigurationManager.getInstance().getProperty("NoneMovement")) {
			movement = movementFactory.getNoneMovement();
		} else {
			throw new IllegalArgumentException("No movement enabled");
		}
		
		if (ConfigurationManager.getInstance().getProperty("GuessFactor")) {
			targeting = targetingFactory.getGFTargeting();
		} else if (ConfigurationManager.getInstance().getProperty("Linear")) {
			targeting = targetingFactory.getLinearTargeting();
		} else if (ConfigurationManager.getInstance().getProperty("NoneTargeting")) {
			targeting = targetingFactory.getNoneTargeting();
		} else {
			throw new IllegalArgumentException("No targeting enabled");
		}
		
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);

		do {
			// basic mini-radar code
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
		} while (true);
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		movement.onScannedRobot(event);
		targeting.onScannedRobot(event);
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		movement.onHitByBullet(event);
	}

	@Override
	public void onDeath(DeathEvent event) {
		movement.onDeath(event);
	}
	
	@Override
	public void onBulletHit(BulletHitEvent event) {
		movement.onBulletHit(event);
	}
}
