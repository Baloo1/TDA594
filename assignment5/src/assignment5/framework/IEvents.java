package assignment5.framework;

import robocode.*;

public interface IEvents {
	public void onScannedRobot(ScannedRobotEvent e);
	public void onHitByBullet(HitByBulletEvent e);
	public void onDeath(DeathEvent e);
	public void onBulletHit(BulletHitEvent e);
}
