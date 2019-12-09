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
	public void onScannedRobot(ScannedRobotEvent e) {
		movement.onScannedRobot(e);
		targeting.onScannedRobot(e);
	}

	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		movement.onHitByBullet(e);
	}

	@Override
	public void onDeath(DeathEvent e) {
		movement.onDeath(e);
	}
	
	@Override
	public void onBulletHit(BulletHitEvent e) {
		movement.onBulletHit(e);
	}
}
