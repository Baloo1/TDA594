import Mock.MockAdvancedRobot;
import assignment6.framework.targeting.LinearTargeting;
import org.junit.Before;
import org.junit.Test;
import robocode.ScannedRobotEvent;

import static org.junit.Assert.*;

public class LinearTargetingTest {
    private MockAdvancedRobot MockRobot;
    private LinearTargeting LinearTargetingTester;

    @Before
    public void before() {
        MockRobot = new MockAdvancedRobot();
        LinearTargetingTester = new LinearTargeting(MockRobot);
    }

    @Test
    public void getEnemyYTest(){
        ScannedRobotEvent event = new ScannedRobotEvent("name", 0, 3, 3, 0, 1);
        MockRobot.setY(7);
        assertEquals(7, (LinearTargetingTester.getEnemyY(MockRobot, event,Math.PI/2)),0);
        MockRobot.setY(-7);
        assertEquals(-7, (LinearTargetingTester.getEnemyY(MockRobot, event,Math.PI/2)),0);
        MockRobot.setY(1);
        assertEquals(-2,(Math.round(LinearTargetingTester.getEnemyY(MockRobot, event,Math.PI))),0);
    }

    @Test
    public void getEnemyXTest(){
        ScannedRobotEvent event = new ScannedRobotEvent("name", 0, 3, 3, 0, 1);
        MockRobot.setX(7);
        assertEquals(10, (LinearTargetingTester.getEnemyX(MockRobot, event,Math.PI/2)),0);
        MockRobot.setX(-7);
        assertEquals(-4, (LinearTargetingTester.getEnemyX(MockRobot, event,Math.PI/2)),0);
        MockRobot.setX(1);
        assertEquals(1,(Math.round(LinearTargetingTester.getEnemyX(MockRobot, event,Math.PI))),0);
    }

    @Test
    public void getAbsoluteBearingTest(){
        ScannedRobotEvent event = new ScannedRobotEvent("name", 0, 3, 0, 0, 1);
        MockRobot.setHeadingRadians(3);
        assertEquals(6,(LinearTargetingTester.getAbsoluteBearing(MockRobot, event)),0);
    }

    @Test
    public void getBulletPowerTest(){
        assertTrue(LinearTargetingTester.getBulletPower(3) == 3);
        assertTrue(LinearTargetingTester.getBulletPower(4) == 3);
        assertTrue(LinearTargetingTester.getBulletPower(1) == 1);
    }

    @Test
    public void getThetaTest(){
        assertEquals(1,(Math.round(LinearTargetingTester.getTheta(Math.PI/2,1))),0);
    }

}