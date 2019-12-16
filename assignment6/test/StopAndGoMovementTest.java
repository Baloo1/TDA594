import assignment6.framework.movement.StopAndGoMovement;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.*;
import robocode.AdvancedRobot;
import robocode.Bullet;
import robocode.HitByBulletEvent;

public class StopAndGoMovementTest {

    @Test
    public void onScannedRobotTest() {
        AdvancedRobot robot = new AdvancedRobot();
        StopAndGoMovement tester = new StopAndGoMovement(robot);
        assert
    }

    @Test
    public void onHitByBulletTest() {
        HitByBulletEvent event = new HitByBulletEvent(2/Math.PI, new Bullet(2/Math.PI, 0,0, 1000, "Attacker", "Defender", true, 1));

        AdvancedRobot robot = new AdvancedRobot();
        StopAndGoMovement tester = new StopAndGoMovement(robot);

        tester.onHitByBullet(event);
        assertEquals(tester.lastEnemyEnergy, 9, 0);
    }

}
