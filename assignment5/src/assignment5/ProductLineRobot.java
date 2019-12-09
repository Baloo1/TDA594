package assignment5;

import java.util.ArrayList;

import robocode.*;
import assignment5.framework.targeting.*;
import assignment5.framework.movement.*;

public class ProductLineRobot extends AdvancedRobot {
	private Movement movement;
	private Targeting targeting;
	
	public void run() {
		
		movement = new WaveSurfing();
		targeting = new GFTargeting();
	
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);

		do {
			// basic mini-radar code
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
		} while (true);
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		movement.onScannedRobot(e);
		targeting.onScannedRobot(e);
	}
}
