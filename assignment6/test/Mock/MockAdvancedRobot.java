package Mock;

import robocode.AdvancedRobot;

public class MockAdvancedRobot extends AdvancedRobot {
  private int roundNum = 0;

  @Override
  public int getRoundNum() {
   return roundNum;
  }

  public void setRoundNum(int round) {
    this.roundNum=round;
  }

}
