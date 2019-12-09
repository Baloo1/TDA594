package assignment5.framework.targeting;

import assignment5.framework.IEvents;
import robocode.*;

public abstract class AbstractTargeting implements IEvents {
	
	protected AdvancedRobot robot;
	
	public AbstractTargeting(AdvancedRobot robot) {
		this.robot = robot;		
	}
	
	@Override
	public abstract void onScannedRobot(ScannedRobotEvent e);

	@Override
	public abstract void onHitByBullet(HitByBulletEvent e);

	@Override
	public abstract void onDeath(DeathEvent e);

	@Override
	public abstract void onBulletHit(BulletHitEvent e);
}
