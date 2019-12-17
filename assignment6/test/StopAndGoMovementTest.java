import assignment6.framework.movement.StopAndGoMovement;
import static org.junit.Assert.*;
import org.junit.Test;
import robocode.AdvancedRobot;
import robocode.Bullet;
import robocode.HitByBulletEvent;

public class StopAndGoMovementTest {
    AdvancedRobot robot = new AdvancedRobot();
    StopAndGoMovement tester = new StopAndGoMovement(robot);

    @Test
    public void onScannedRobotTest() {

    }

    @Test
    public void onHitByBulletTest() {
        HitByBulletEvent event = new HitByBulletEvent(2/Math.PI, new Bullet(2/Math.PI, 0,0, 1000, "Attacker", "Defender", true, 1));

        tester.onHitByBullet(event);
        assertEquals(tester.getLastEnemyEnergy(), 9, 0);
    }

    @Test
    public void onDeathTest(){

    }

    @Test
    public  void onBulletHitTest(){

    }

}
