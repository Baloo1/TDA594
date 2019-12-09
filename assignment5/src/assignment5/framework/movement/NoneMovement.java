package assignment5.framework.movement;

import robocode.AdvancedRobot;
import robocode.BulletHitEvent;
import robocode.DeathEvent;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;

public class NoneMovement extends AbstractMovement {
	public NoneMovement(AdvancedRobot robot) {
		super(robot);
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath(DeathEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBulletHit(BulletHitEvent e) {
		// TODO Auto-generated method stub
		
	}
}
