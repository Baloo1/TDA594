import assignment6.framework.movement.StopAndGoMovement;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.*;

import robocode.*;

public class StopAndGoMovementTest {
    private AdvancedRobot robot = new AdvancedRobot();
    private StopAndGoMovement tester = new StopAndGoMovement(robot);

    //TODO: create test for onScannedRobotTest
    @Test
    public void onScannedRobotTest() {
    }

    @Test
    public void onHitByBulletTest() {
        HitByBulletEvent event = new HitByBulletEvent(2/Math.PI, new Bullet(2/Math.PI, 0,0, 1000, "Attacker", "Defender", true, 1));

        tester.onHitByBullet(event);
        assertEquals(tester.getLastEnemyEnergy(), 9, 0);
    }

    //TODO: Create test for method onDeath
    @Test
    public void onDeathTest(){
    }

    @Test
    public void onBulletHitTest() {
        BulletHitEvent event = new BulletHitEvent("robot", 10, new Bullet (2/Math.PI, 0,0, 1000, "Attacker", "Defender", true, 1) );
        tester.onBulletHit(event);
        assertEquals(tester.getLastEnemyEnergy(), -10, 0);
    }

    @Test
    public void setDeltaTest(){
        ScannedRobotEvent event = new ScannedRobotEvent("robot", 10, 0,0,0,0,true);
        assertEquals(tester.setDelta(event), -10, 0);
    }

}
