package assignment6.framework.targeting;

import robocode.*;

import java.awt.geom.Point2D;
import java.util.Hashtable;


public class HeadOnTargeting extends AbstractTargeting {

        static Point2D myLocation;
        static Hashtable enemies;

        HeadOnTargeting(AdvancedRobot robot) {
            super(robot);
            robot.setTurnRadarRight(Double.POSITIVE_INFINITY);
            enemies = new Hashtable();
            do
            {
                myLocation = new Point2D.Double(robot.getX(), robot.getY());
                robot.execute();
            }
            while (true);
        }

        public void onScannedRobot(ScannedRobotEvent e) {
            String name;
            MicroEnemyInfo enemy = (MicroEnemyInfo)enemies.get(name = e.getName());
            if (enemy == null)
                enemies.put(name, enemy = new MicroEnemyInfo());
            //wasn't making my enemies Point2D objects a good idea?
            enemy.energy = e.getEnergy();
            enemy.setLocation(projectPoint(myLocation, robot.getHeadingRadians()+e.getBearingRadians(), e.getDistance()));
        }

        //utility functions, I figure anyone making a goto bot has something like these, and they
        //come in useful in a lot of other random places, too:
        private static Point2D projectPoint(Point2D startPoint, double theta, double dist)
        {
            return new Point2D.Double(startPoint.getX() + dist * Math.sin(theta), startPoint.getY() + dist * Math.cos(theta));
        }

        public class MicroEnemyInfo extends Point2D.Double //Be the Point2D.  You ARE the Point2D...
        {
            double energy;
        }

}


