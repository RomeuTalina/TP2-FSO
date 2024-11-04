import robot.RobotLegoEV3;

public class BD {

	private RobotLegoEV3 robot;
	private String nomeRobot;
	private boolean robotAberto = false;
	private int angulo;
	private int distancia;
	private int raio;
	private boolean onOFF = false;
	private boolean terminar;
	private boolean vaguear, evitar, fugir;

	public BD() {
		robot = new RobotLegoEV3();
	}
	
	public boolean isEvitar() {
		return evitar;
	}

	public void setEvitar(boolean evitar) {
		this.evitar = evitar;
	}

	public boolean isFugir() {
		return fugir;
	}

	public void setFugir(boolean fugir) {
		this.fugir = fugir;
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

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public boolean isOnOFF() {
		return onOFF;
	}

	public void setOnOFF(boolean onOFF) {
		this.onOFF = onOFF;
	}

	public boolean isTerminar() {
		return terminar;
	}

	public void setTerminar(boolean terminar) {
		this.terminar = terminar;
	}
	
	public int getAngulo() {
		return angulo;
	}
	
	public void setAngulo(int angulo) {
		this.angulo = angulo;
	}
	public int getRaio() {
		return raio;
	}
	public void setRaio(int raio) {
		this.raio = raio; 
	}
	public boolean isVaguear() {
		return vaguear;
	}
	public void setVaguear(boolean v) {
		vaguear = v;
	}
	public void setRobotAberto(boolean aberto) {
		robotAberto = aberto;
	}
	public boolean getRobotAberto() {
		return robotAberto;
	}
}
