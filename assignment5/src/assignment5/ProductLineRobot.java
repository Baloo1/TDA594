package assignment5;

import robocode.*;
import assignment5.framework.targeting.*;
import assignment5.config.ConfigurationManager;
import assignment5.framework.movement.*;
 

public class ProductLineRobot extends AdvancedRobot implements IEventsTarget, IEventsMovement {
	private AbstractMovement movement;
	private AbstractTargeting targeting;
	
	@Override
	public void run() {
		if (ConfigurationManager.getInstance().getProperty("WaveSurfing")) {
			movement = new WaveSurfingMovement(this);
		} else if (ConfigurationManager.getInstance().getProperty("RandomFluidOrbit")) {
			movement = new RandomFluidOrbitMovement(this);
		} else if (ConfigurationManager.getInstance().getProperty("StopAndGo")) {
			movement = new StopAndGoMovement(this);
		} else if (ConfigurationManager.getInstance().getProperty("NoneMovement")) {
			movement = new NoneMovement(this);
		} else {
			throw new IllegalArgumentException("No movement enabled");
		}
		
		if (ConfigurationManager.getInstance().getProperty("GuessFactor")) {
			targeting = new GFTargeting(this);
		} else if (ConfigurationManager.getInstance().getProperty("Linear")) {
			targeting = new LinearTargeting(this);
		} else if (ConfigurationManager.getInstance().getProperty("NoneTargeting")) {
			targeting = new NoneTargeting(this);
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
