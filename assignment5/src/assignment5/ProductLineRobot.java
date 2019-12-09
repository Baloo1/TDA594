package assignment5;

import robocode.*;
import assignment5.framework.targeting.*;
import assignment5.framework.movement.*;

public class ProductLineRobot extends AdvancedRobot implements IEventsTarget, IEventsMovement {
	private AbstractMovement movement;
	private AbstractTargeting targeting;
	
	@Override
	public void run() {
		
		movement = new WaveSurfingMovement(this);
		targeting = new LinearTargeting(this);
	
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
