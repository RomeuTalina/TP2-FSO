package ig;

import robot.RobotLegoEV3;

public class BD {
	private RobotLegoEV3 robot;
	private String nomeRobot;
	private boolean robotAberto = false;
	
	public BD() {
		robot = new RobotLegoEV3();
	}

	public RobotLegoEV3 getRobot() {
		return robot;
	}

	public void setRobot(RobotLegoEV3 robot) {
		this.robot = robot;
	}

	public String getNomeRobot() {
		return nomeRobot;
	}

	public void setNomeRobot(String nomeRobot) {
		this.nomeRobot = nomeRobot;
	}

	public boolean isRobotAberto() {
		return robotAberto;
	}

	public void setRobotAberto(boolean robotAberto) {
		this.robotAberto = robotAberto;
	}
	
	
}
