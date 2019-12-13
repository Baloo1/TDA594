package assignment5.framework.movement;

import robocode.*;

public abstract class AbstractMovement implements IEventsMovement {
	
	protected AdvancedRobot robot;
	
	public AbstractMovement(AdvancedRobot robot) {
		this.robot = robot;		
	}
	
	public abstract void onScannedRobot(ScannedRobotEvent event); 
	
	public abstract void onHitByBullet(HitByBulletEvent event); 
	
	public abstract void onDeath(DeathEvent event);
	
	public abstract void onBulletHit(BulletHitEvent event); 
	
}
