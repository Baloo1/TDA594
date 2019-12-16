import assignment6.framework.targeting.AbstractTargeting;
import assignment6.framework.targeting.LinearTargeting;
import org.junit.Test;
import robocode.AdvancedRobot;

public class LinearTargetingTest {
    @Test(expected = AssertionError.class)
    public void nullEvent() {
            new LinearTargeting(null);
    }
}
