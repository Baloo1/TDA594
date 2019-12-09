package assignment5.framework;

import robocode.*;

public interface IEvents {
	void onScannedRobot(ScannedRobotEvent e);
	void onHitByBullet(HitByBulletEvent e);
	void onDeath(DeathEvent e);
	void onBulletHit(BulletHitEvent e);
}
