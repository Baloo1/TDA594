package Mock;

import robocode.AdvancedRobot;

public class MockAdvancedRobot extends AdvancedRobot {
  private int roundNum = 0;
  private double headingRadians = 0;
  private double x = 0;
  private double y = 0;
  private double turnRightRadians = 0;
  private double ahead = 0;
  private double radarRight = 0;
  private double bearing = 0;

  @Override
  public int getRoundNum() {
   return roundNum;
  }

  public void setRoundNum(int round) {
    this.roundNum=round;
  }

  @Override
  public double getHeadingRadians() {
    return headingRadians;
  }

  public void setHeadingRadians(double heading) {
    this.headingRadians = heading;
  }

  @Override
  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  @Override
  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  @Override
  public void setTurnRightRadians(double rightRadians) {
    this.turnRightRadians = rightRadians;
  }

  @Override
  public void setAhead(double ahead) {
    this.ahead = ahead;
  }

  public void setBearingRadians(double bearing){
    this.bearing = bearing;
  }

/*
  @Override
  public void setTurnRadarRight( double radarRight){
    this.radarRight = radarRight;
  }

 */
}
