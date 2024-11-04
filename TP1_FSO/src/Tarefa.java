import java.util.concurrent.Semaphore;

public abstract class Tarefa extends Thread{
	
	protected BD bd;
	protected Application app;
	protected GUIRobot gui;
	protected int ESTADO, curva1;
	protected final int BLOQUEADO = 0, PAUSADO = 1, EXECUTAR = 2, DORMIR = 3;
	protected Semaphore bloqueio;
	
	public Tarefa(BD bd, Application app, GUIRobot gui) {
		this.bd = bd;
		this.app = app;
		this.gui = gui;
	}
	public abstract void run();
	
	public void comecar() {
		bloqueio.release();
	}
	
	public void fechar() {
		bloqueio.drainPermits();
	}
}

