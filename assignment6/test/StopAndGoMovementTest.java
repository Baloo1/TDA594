import assignment6.framework.movement.StopAndGoMovement;
import org.junit.Test;
import robocode.AdvancedRobot;

public class StopAndGoMovementTest {

    @Test
    public void onScannedRobotTest() {
        AdvancedRobot robot = new AdvancedRobot();
        StopAndGoMovement tester = new StopAndGoMovement(robot);
    }
}