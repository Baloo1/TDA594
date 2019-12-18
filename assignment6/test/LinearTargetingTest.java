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

    }

    @Test
    public void getEnemyXTest(){

    }

    @Test
    public void getAbsoluteBearingTest(){
        //THIS IS WHERE THE SHIT HAPPENS :D
        //ScannedRobotEvent event = new ScannedRobotEvent();
       // LinearTargetingTester.getAbsoluteBearing(LinearTargetingTester, event);

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