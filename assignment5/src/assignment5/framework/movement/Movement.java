package assignment5.framework.movement;

import assignment5.framework.IEvents;
import robocode.*;

public abstract class Movement implements IEvents {
	
	protected AdvancedRobot robot;
	
	public Movement(AdvancedRobot robot) {
		this.robot = robot;		
	}
	
	public void onScannedRobot(ScannedRobotEvent e) { } 
	public void onHitByBullet(HitByBulletEvent e) { } 
	public void onDeath(DeathEvent e) { }
	public void onBulletHit(BulletHitEvent e) { } 
}
