package assignment5.framework.movement;

import assignment5.framework.IEventsTarget;
import robocode.*;

public abstract class AbstractMovement implements IEventsTarget {
	
	protected AdvancedRobot robot;
	
	public AbstractMovement(AdvancedRobot robot) {
		this.robot = robot;		
	}
	
	public abstract void onScannedRobot(ScannedRobotEvent e); 
	
	public abstract void onHitByBullet(HitByBulletEvent e); 
	
	public abstract void onDeath(DeathEvent e);
	
	public abstract void onBulletHit(BulletHitEvent e); 
	
}
