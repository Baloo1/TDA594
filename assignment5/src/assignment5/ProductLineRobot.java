package assignment5;

import robocode.*;
import assignment5.framework.targeting.*;
import assignment5.framework.IEvents;
import assignment5.framework.movement.*;

public class ProductLineRobot extends AdvancedRobot implements IEvents {
	private Movement movement;
	private Targeting targeting;
	
	public void run() {
		
		movement = new WaveSurfing(this);
		targeting = new GFTargeting(this);
	
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);

		do {
			// basic mini-radar code
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
		} while (true);
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		movement.onScannedRobot(e);
		targeting.onScannedRobot(e);
	}
	
	public void onHitByBullet(HitByBulletEvent e) {
		movement.onHitByBullet(e);
		targeting.onHitByBullet(e);
	}
	public void onDeath(DeathEvent e) {
		movement.onDeath(e);
		targeting.onDeath(e);
	}
	public void onBulletHit(BulletHitEvent e) {
		movement.onBulletHit(e);
		targeting.onBulletHit(e);
	}
}
