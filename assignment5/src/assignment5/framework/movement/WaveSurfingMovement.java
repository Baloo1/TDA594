package assignment5.framework.movement;

import robocode.*; // for Double and Integer objects
import robocode.util.Utils;

import java.awt.geom.*; // for Point2D's
import java.util.ArrayList;
import java.util.List;


public class WaveSurfingMovement extends AbstractMovement {
	public static final int BINS = 47;
	public static double surfStats[] = new double[BINS]; // we'll use 47 bins
	public Point2D.Double myLocation; // our bot's location
	public Point2D.Double enemyLocation; // enemy bot's location

	public List<EnemyWave> enemyWaves;
	public List<Integer> surfDirections;
	public List<Double> surfAbsBearings;
	
	public static double _oppEnergy = 100.0;
	public static Rectangle2D.Double _fieldRect = new java.awt.geom.Rectangle2D.Double(18, 18, 764, 564);
	public static double WALL_STICK = 160;
	
	public WaveSurfingMovement(AdvancedRobot robot) {
		super(robot);
		
		enemyWaves = new ArrayList<EnemyWave>();
		surfDirections = new ArrayList<Integer>();
		surfAbsBearings = new ArrayList<Double>();
	}
	
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
        myLocation = new Point2D.Double(robot.getX(), robot.getY());

        double lateralVelocity = robot.getVelocity()*Math.sin(e.getBearingRadians());
        double absBearing = e.getBearingRadians() + robot.getHeadingRadians();

        robot.setTurnRadarRightRadians(Utils.normalRelativeAngle(absBearing - robot.getRadarHeadingRadians()) * 2);

        surfDirections.add(0, (lateralVelocity >= 0) ? 1 : -1);
        surfAbsBearings.add(0, absBearing + Math.PI);


        double bulletPower = _oppEnergy - e.getEnergy();
        if (bulletPower < 3.01 && bulletPower > 0.09
            && surfDirections.size() > 2) {
            EnemyWave ew = new EnemyWave();
            ew.fireTime = robot.getTime() - 1;
            ew.bulletVelocity = bulletVelocity(bulletPower);
            ew.distanceTraveled = bulletVelocity(bulletPower);
            ew.direction = ((Integer)surfDirections.get(2)).intValue();
            ew.directAngle = ((Double)surfAbsBearings.get(2)).doubleValue();
            ew.fireLocation = (Point2D.Double)enemyLocation.clone(); // last tick

            enemyWaves.add(ew);
        }

        _oppEnergy = e.getEnergy();

        // update after EnemyWave detection, because that needs the previous
        // enemy location as the source of the wave
        enemyLocation = project(myLocation, absBearing, e.getDistance());

        updateWaves();
        doSurfing();
		
	}
	
	private void updateWaves() {
        for (int x = 0; x < enemyWaves.size(); x++) {
            EnemyWave ew = (EnemyWave)enemyWaves.get(x);

            ew.distanceTraveled = (robot.getTime() - ew.fireTime) * ew.bulletVelocity;
            if (ew.distanceTraveled >
                myLocation.distance(ew.fireLocation) + 50) {
                enemyWaves.remove(x);
                x--;
            }
        }
    }

    private EnemyWave getClosestSurfableWave() {
        double closestDistance = 50000; // I juse use some very big number here
        EnemyWave surfWave = null;

        for (int x = 0; x < enemyWaves.size(); x++) {
            EnemyWave ew = (EnemyWave)enemyWaves.get(x);
            double distance = myLocation.distance(ew.fireLocation)
                - ew.distanceTraveled;

            if (distance > ew.bulletVelocity && distance < closestDistance) {
                surfWave = ew;
                closestDistance = distance;
            }
        }

        return surfWave;
    }

    // Given the EnemyWave that the bullet was on, and the point where we
    // were hit, calculate the index into our stat array for that factor.
    private static int getFactorIndex(EnemyWave ew, Point2D.Double targetLocation) {
        double offsetAngle = (absoluteBearing(ew.fireLocation, targetLocation)
            - ew.directAngle);
        double factor = Utils.normalRelativeAngle(offsetAngle)
            / maxEscapeAngle(ew.bulletVelocity) * ew.direction;

        return (int)limit(0,
            (factor * ((BINS - 1) / 2)) + ((BINS - 1) / 2),
            BINS - 1);
    }

    // Given the EnemyWave that the bullet was on, and the point where we
    // were hit, update our stat array to reflect the danger in that area.
    private void logHit(EnemyWave ew, Point2D.Double targetLocation) {
        int index = getFactorIndex(ew, targetLocation);

        for (int x = 0; x < BINS; x++) {
            // for the spot bin that we were hit on, add 1;
            // for the bins next to it, add 1 / 2;
            // the next one, add 1 / 5; and so on...
            surfStats[x] += 1.0 / (Math.pow(index - x, 2) + 1);
        }
    }

    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // If the _enemyWaves collection is empty, we must have missed the
        // detection of this wave somehow.
        if (!enemyWaves.isEmpty()) {
            Point2D.Double hitBulletLocation = new Point2D.Double(
                e.getBullet().getX(), e.getBullet().getY());
            EnemyWave hitWave = null;

            // look through the EnemyWaves, and find one that could've hit us.
            for (int x = 0; x < enemyWaves.size(); x++) {
                EnemyWave ew = (EnemyWave)enemyWaves.get(x);

                if (Math.abs(ew.distanceTraveled -
                    myLocation.distance(ew.fireLocation)) < 50
                    && Math.abs(bulletVelocity(e.getBullet().getPower()) 
                        - ew.bulletVelocity) < 0.001) {
                    hitWave = ew;
                    break;
                }
            }

            if (hitWave != null) {
                logHit(hitWave, hitBulletLocation);

                // We can remove this wave now, of course.
                enemyWaves.remove(enemyWaves.lastIndexOf(hitWave));
            }
        }
    }

    // CREDIT: mini sized predictor from Apollon, by rozu
    // http://robowiki.net?Apollon
    private Point2D.Double predictPosition(EnemyWave surfWave, int direction) {
    	Point2D.Double predictedPosition = (Point2D.Double)myLocation.clone();
    	double predictedVelocity = robot.getVelocity();
    	double predictedHeading = robot.getHeadingRadians();
    	double maxTurning, moveAngle, moveDir;

        int counter = 0; // number of ticks in the future
        boolean intercepted = false;

    	do {
    		moveAngle =
                wallSmoothing(predictedPosition, absoluteBearing(surfWave.fireLocation,
                predictedPosition) + (direction * (Math.PI/2)), direction)
                - predictedHeading;
    		moveDir = 1;

    		if(Math.cos(moveAngle) < 0) {
    			moveAngle += Math.PI;
    			moveDir = -1;
    		}

    		moveAngle = Utils.normalRelativeAngle(moveAngle);

    		// maxTurning is built in like this, you can't turn more then this in one tick
    		maxTurning = Math.PI/720d*(40d - 3d*Math.abs(predictedVelocity));
    		predictedHeading = Utils.normalRelativeAngle(predictedHeading
                + limit(-maxTurning, moveAngle, maxTurning));

    		// this one is nice ;). if predictedVelocity and moveDir have
            // different signs you want to breack down
    		// otherwise you want to accelerate (look at the factor "2")
    		predictedVelocity += (predictedVelocity * moveDir < 0 ? 2*moveDir : moveDir);
    		predictedVelocity = limit(-8, predictedVelocity, 8);

    		// calculate the new predicted position
    		predictedPosition = project(predictedPosition, predictedHeading, predictedVelocity);

            counter++;

            if (predictedPosition.distance(surfWave.fireLocation) <
                surfWave.distanceTraveled + (counter * surfWave.bulletVelocity)
                + surfWave.bulletVelocity) {
                intercepted = true;
            }
    	} while(!intercepted && counter < 500);

    	return predictedPosition;
    }

    private double checkDanger(EnemyWave surfWave, int direction) {
        int index = getFactorIndex(surfWave,
            predictPosition(surfWave, direction));

        return surfStats[index];
    }
   
    private void doSurfing() {
        EnemyWave surfWave = getClosestSurfableWave();

        if (surfWave == null) { return; }

        double dangerLeft = checkDanger(surfWave, -1);
        double dangerRight = checkDanger(surfWave, 1);

        double goAngle = absoluteBearing(surfWave.fireLocation, myLocation);
        if (dangerLeft < dangerRight) {
            goAngle = wallSmoothing(myLocation, goAngle - (Math.PI/2), -1);
        } else {
            goAngle = wallSmoothing(myLocation, goAngle + (Math.PI/2), 1);
        }

        setBackAsFront(robot, goAngle);
    }


    // This can be defined as an inner class if you want.
    private class EnemyWave {
        Point2D.Double fireLocation;
        long fireTime;
        double bulletVelocity, directAngle, distanceTraveled;
        int direction;

        private EnemyWave() { }
    }
  
    
   
    // CREDIT: Iterative WallSmoothing by Kawigi
    //   - return absolute angle to move at after account for WallSmoothing
    // robowiki.net?WallSmoothing
    private double wallSmoothing(Point2D.Double botLocation, double angle, int orientation) {
        while (!_fieldRect.contains(project(botLocation, angle, 160))) {
            angle += orientation*0.05;
        }
        return angle;
    }

    // CREDIT: from CassiusClay, by PEZ
    //   - returns point length away from sourceLocation, at angle
    // robowiki.net?CassiusClay
    private static Point2D.Double project(Point2D.Double sourceLocation, double angle, double length) {
        return new Point2D.Double(sourceLocation.x + Math.sin(angle) * length,
            sourceLocation.y + Math.cos(angle) * length);
    }

    // got this from RaikoMicro, by Jamougha, but I think it's used by many authors
    //  - returns the absolute angle (in radians) from source to target points
    private static double absoluteBearing(Point2D.Double source, Point2D.Double target) {
        return Math.atan2(target.x - source.x, target.y - source.y);
    }

    private static double limit(double min, double value, double max) {
        return Math.max(min, Math.min(value, max));
    }

    private static double bulletVelocity(double power) {
        return (20D - (3D*power));
    }

    private static double maxEscapeAngle(double velocity) {
        return Math.asin(8.0/velocity);
    }

    private static void setBackAsFront(AdvancedRobot robot, double goAngle) {
        double angle =
            Utils.normalRelativeAngle(goAngle - robot.getHeadingRadians());
        if (Math.abs(angle) > (Math.PI/2)) {
            if (angle < 0) {
                robot.setTurnRightRadians(Math.PI + angle);
            } else {
                robot.setTurnLeftRadians(Math.PI - angle);
            }
            robot.setBack(100);
        } else {
            if (angle < 0) {
                robot.setTurnLeftRadians(-1*angle);
           } else {
                robot.setTurnRightRadians(angle);
           }
            robot.setAhead(100);
        }
    }

	@Override
	public void onDeath(DeathEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBulletHit(BulletHitEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
