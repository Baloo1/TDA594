package assignment6.framework.targeting;

import robocode.*;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;


public class HeadOnTargeting extends AbstractTargeting {

    static Point2D myLocation;
    static Hashtable enemies;
    public static MicroEnemyInfo currentTarget;

    public HeadOnTargeting(AdvancedRobot robot) {
        super(robot);
        Point2D myLocation, last;
        robot.setTurnRadarRight(Double.POSITIVE_INFINITY);
        enemies = new Hashtable();
        Point2D next = currentTarget = null;
        do
        {
            myLocation = new Point2D.Double(robot.getX(), robot.getY());
            if (currentTarget != null)
            {
                if (next == null)
                    next = last = myLocation;
                boolean changed = false;
                double angle = 0, distance =0;
                //sometimes I'll change my point here, sometimes I won't.  It's a big mystery!
                do
                {
                    angle += .1;
                }
            while (angle < Math.PI * 2);
            //this helps the whole movement system know what direction I'm going, and avoid head-on targeting:
            if (robot.getEnergy() / distance > .005)
                robot.setFire(60 * Math.min(currentTarget.energy, robot.getEnergy()) / distance);
            robot.setTurnGunRightRadians(robocode.util.Utils.normalRelativeAngle(angle(currentTarget, myLocation) - robot.getGunHeadingRadians()));
            double turn;
            if (Math.cos(turn = angle(next, myLocation) - robot.getHeadingRadians()) < 0) {
                turn += Math.PI;
                distance = -distance;
            }
            robot.setTurnRightRadians(robocode.util.Utils.normalRelativeAngle(turn));
            robot.setAhead((Math.abs(robot.getTurnRemainingRadians()) > 1) ? 0 : distance);
        }
        //robot.execute();
    }
        while(true);
}

    public void onScannedRobot(ScannedRobotEvent e) {
        String name;
        MicroEnemyInfo enemy = (MicroEnemyInfo) enemies.get(name = e.getName());
        if (enemy == null)
            enemies.put(name, enemy = new MicroEnemyInfo());
        enemy.energy = e.getEnergy();
        enemy.setLocation(projectPoint(myLocation, robot.getHeadingRadians() + e.getBearingRadians(), e.getDistance()));
        if ( (currentTarget == null || targetability(enemy) < targetability(currentTarget)-100))
            currentTarget = enemy;
    }

    public static double targetability(MicroEnemyInfo e) {
        return myLocation.distance(e) - e.energy;
    }

    private static Point2D projectPoint(Point2D startPoint, double theta, double dist) {
        return new Point2D.Double(startPoint.getX() + dist * Math.sin(theta), startPoint.getY() + dist * Math.cos(theta));
    }

    public double angle(Point2D point2, Point2D point1) {
        return Math.atan2(point2.getX() - point1.getX(), point2.getY() - point1.getY());
    }

    public class MicroEnemyInfo extends Point2D.Double {
        double energy;
    }

}


