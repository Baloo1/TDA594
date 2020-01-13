import Mock.MockAdvancedRobot;
import assignment6.framework.movement.StopAndGoMovement;

import org.junit.Before;
import org.junit.Test;

import robocode.*;

import static org.junit.Assert.*;


public class StopAndGoMovementTest {
    private MockAdvancedRobot MockRobot;
    private StopAndGoMovement StopAndGoTester;

    @Before
    public void before() {
        MockRobot = new MockAdvancedRobot();
        StopAndGoTester = new StopAndGoMovement(MockRobot);
    }

    @Test
    public void onScannedRobotTest() {
        ScannedRobotEvent event = new ScannedRobotEvent();

//        StopAndGoTester.onScannedRobot(event);
    }

    @Test
    public void onHitByBulletTest() {
        HitByBulletEvent event = new HitByBulletEvent(2/Math.PI, new Bullet(2/Math.PI, 0,0, 1000, "Attacker", "Defender", true, 1));

        StopAndGoTester.onHitByBullet(event);
        assertEquals(9, StopAndGoTester.getLastEnemyEnergy(), 0);
    }

    @Test
    public void onDeathLowRoundTest(){
        DeathEvent event = new DeathEvent();

        StopAndGoTester.onDeath(event);
        assertTrue(StopAndGoTester.isFlat());
    }

    @Test
    public void onDeathHighRoundTest(){
        DeathEvent event = new DeathEvent();
        MockRobot.setRoundNum(4);
        StopAndGoTester.onDeath(event);
        assertFalse(StopAndGoTester.isFlat());
    }

    @Test
    public void onBulletHitTest() {
        BulletHitEvent event = new BulletHitEvent("robot", 10, new Bullet (2/Math.PI, 0,0, 1000, "Attacker", "Defender", true, 1) );
        StopAndGoTester.onBulletHit(event);
        assertEquals(-1, StopAndGoTester.getLastEnemyEnergy(), 0);
    }

    @Test
    public void setDeltaTest(){
        ScannedRobotEvent event = new ScannedRobotEvent("robot", 10, 0,0,0,0,true);
        assertEquals(-11, StopAndGoTester.setDelta(event), 0);
    }
}
