package assignment6.framework.movement;

import robocode.*;

public interface IEventsMovement {
	void onScannedRobot(ScannedRobotEvent event);
	void onHitByBullet(HitByBulletEvent event);
	void onDeath(DeathEvent event);
	void onBulletHit(BulletHitEvent event);
}


