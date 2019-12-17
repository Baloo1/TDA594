import assignment6.framework.movement.StopAndGoMovement;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.*;

import robocode.*;

public class StopAndGoMovementTest {

    @Test
    public void onScannedRobotTest() {
        AdvancedRobot robot = new AdvancedRobot();
        StopAndGoMovement tester = new StopAndGoMovement(robot);
    }

    @Test
    public void onHitByBulletTest() {
       // HitByBulletEvent event = new HitByBulletEvent();
        HitByBulletEvent event = new HitByBulletEvent(2/Math.PI, new Bullet(2/Math.PI, 0,0, 1000, "Attacker", "Defender", true, 1));

        AdvancedRobot robot = new AdvancedRobot();
        StopAndGoMovement tester = new StopAndGoMovement(robot);

        tester.onHitByBullet(event);
        assertEquals(tester.lastEnemyEnergy, 9, 0);
    }

    @Test
    public void onBulletHitTest() {
        BulletHitEvent event = new BulletHitEvent("robot", 10, new Bullet (2/Math.PI, 0,0, 1000, "Attacker", "Defender", true, 1) );

        AdvancedRobot robot = new AdvancedRobot();
        StopAndGoMovement tester = new StopAndGoMovement(robot);

        tester.onBulletHit(event);
        System.out.println(tester.lastEnemyEnergy);
        assertEquals(tester.lastEnemyEnergy, -10, 0);
    }

    @Test
    public void setDeltaTest(){
        ScannedRobotEvent event = new ScannedRobotEvent("robot", 10, 0,0,0,0,true);

        AdvancedRobot robot = new AdvancedRobot();
        StopAndGoMovement tester = new StopAndGoMovement(robot);

        assertEquals(tester.setDelta(event), -10, 0);
    }


    /*
    @Test
    public void onDeathTest() {
        DeathEvent event = new DeathEvent();

        AdvancedRobot robot = new AdvancedRobot();
        System.out.println(robot.getRoundNum());
        StopAndGoMovement tester = new StopAndGoMovement(robot);

        tester.onDeath(event);

        assertTrue(tester.flat);

    }
     */

}
