import java.util.Random;
import java.util.concurrent.Semaphore;

public class Vaguear extends Tarefa{
	
	private Random rand = new Random();
	private final Semaphore pausa = new Semaphore(1);
	private int next, last;
	
	public Vaguear(BD bd, Application app, GUIRobot gui) {
		super(bd, app, gui);
		bloqueio = new Semaphore(0);
	}
	
	public void run() {
		ESTADO = BLOQUEADO;
		while(!bd.isTerminar()) {
			switch (ESTADO){
				case BLOQUEADO:
				try {
					bloqueio.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					ESTADO = PAUSADO;
					break;
				case PAUSADO:
					try {
						pausa.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ESTADO = EXECUTAR;
					break;
				case EXECUTAR:
					try {
						app.getS().acquire();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					bd.setDistancia(rand.nextInt(51));
					bd.setAngulo(rand.nextInt(181));
					bd.setRaio(rand.nextInt(21));
					next = rand.nextInt(3);
					switch(next) {
						case 0:							
							bd.getRobot().Reta(bd.getDistancia());
							gui.getConsola().append("Vaguear - Reta de "  + bd.getDistancia() + "cm.\n");
							last = 0;
							break;
						case 1:
							bd.getRobot().CurvarEsquerda(bd.getRaio(), bd.getAngulo());
							gui.getConsola().append("Vaguear - Curva para a esquerda de "  + bd.getAngulo() + "graus e raio de " + bd.getRaio() + "\n");
							last = 1;
							break;
						case 2:							
							bd.getRobot().CurvarDireita(bd.getRaio(), bd.getAngulo());
							gui.getConsola().append("Vaguear - Curva para a direita de "  + bd.getAngulo() + "graus e raio de " + bd.getRaio() + "\n");
							last = 1;
							break;
					}
					if(bd.isVaguear()) {
						ESTADO = DORMIR;
					}
					else {
						ESTADO = BLOQUEADO;
					}
					app.getS().release();
					pausa.release();
					break;
				case DORMIR:
					switch(last) {
						case 0:
							try {
								Thread.sleep(bd.getDistancia()/30 * 1000);
							}catch(InterruptedException e) {
								e.printStackTrace();
							}
							break;
						case 1:
							try {
								Thread.sleep((long)(Math.PI * bd.getRaio() * bd.getAngulo())/180);
							}catch(InterruptedException e) {
								e.printStackTrace();
							}
							break;
					}
					if(bd.isVaguear()) {
						ESTADO = PAUSADO;
					}
					else {
						ESTADO = BLOQUEADO;
					}
					break;
			}
		}	
	}
	
	public void setESTADO(int estado) {
		ESTADO = estado;
	}
	
	public void pausar() {
		pausa.drainPermits();
		ESTADO = PAUSADO;
//		System.out.println("vaguear pausado");
	}
	
	public void continuar() {
		pausa.release();
	}
	
}
