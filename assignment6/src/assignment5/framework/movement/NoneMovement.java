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
	public void onScannedRobot(ScannedRobotEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath(DeathEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBulletHit(BulletHitEvent event) {
		// TODO Auto-generated method stub
		
	}
}
