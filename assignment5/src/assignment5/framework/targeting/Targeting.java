package assignment5.framework.targeting;

import assignment5.framework.IEvents;
import robocode.*;

public abstract class Targeting implements IEvents {
	
	protected AdvancedRobot robot;
	
	public Targeting(AdvancedRobot robot) {
		this.robot = robot;		
	}
	
	public void onScannedRobot(ScannedRobotEvent e) { } 
	public void onHitByBullet(HitByBulletEvent e) { } 
	public void onDeath(DeathEvent e) { }
	public void onBulletHit(BulletHitEvent e) { } 
}
