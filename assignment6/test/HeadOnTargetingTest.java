import Mock.MockAdvancedRobot;
import assignment6.framework.targeting.HeadOnTargeting;
import org.junit.Before;
import org.junit.Test;
import robocode.ScannedRobotEvent;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class HeadOnTargetingTest {
    private MockAdvancedRobot MockRobot;
    private HeadOnTargeting HeadOnTargetingTester;

    @Before
    public void before() {
        MockRobot = new MockAdvancedRobot();
        HeadOnTargetingTester = new HeadOnTargeting(MockRobot);
    }

    @Test
    public void onScannedRobot() {
        ScannedRobotEvent event = new ScannedRobotEvent();
        HeadOnTargetingTester.onScannedRobot(event);
        System.out.println(HeadOnTargeting.currentTarget);
    }

    @Test
    public void angleTest(){
        Point2D point1 = new Point2D.Double(1,1);
        Point2D point2 = new Point2D.Double(1,1);
        System.out.println(HeadOnTargetingTester.angle(point2,point1));

    }

}