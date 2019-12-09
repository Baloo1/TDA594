package assignment5.framework.movement;

import robocode.*;

public interface IEventsMovement {
	void onScannedRobot(ScannedRobotEvent e);
	void onHitByBullet(HitByBulletEvent e);
	void onDeath(DeathEvent e);
	void onBulletHit(BulletHitEvent e);
}


