import java.util.Random;
import java.util.concurrent.Semaphore;

public class Fugir extends Tarefa{
	
	
	private Random rand = new Random();
	private Semaphore pausa = new Semaphore(0);
	
	public Fugir(BD bd, Application app, GUIRobot gui) {
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
					ESTADO = EXECUTAR;
					break;
				case PAUSADO:
					try {
						pausa.acquire();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					ESTADO = EXECUTAR;
					break;
				case EXECUTAR:
					if(bd.isVaguear()) {
						app.getVaguear().pausar();
					}
					try {
						app.getS().acquire();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					while(bd.getRobot().SensorUS(bd.getRobot().S_2) < 50) {
						curva1 = rand.nextInt(2);
						switch(curva1) {
							case 0:
								bd.getRobot().CurvarEsquerda(20, 90);
								gui.getConsola().append("Fugir - Curva para a esquerda de 90 graus e raio de 20cm\n");
								break;
							case 1:
								bd.getRobot().CurvarDireita(20, 90);
								gui.getConsola().append("Fugir - Curva para a direita de 90 graus e raio de 20cm\n");
								break;
						}
						bd.getRobot().Reta(20);
						gui.getConsola().append("Fugir - Reta de 20cm para a frente\n");
						switch(curva1) {
							case 0:
								bd.getRobot().CurvarDireita(20, 90);
								gui.getConsola().append("Fugir - Curva para a direita de 90 graus e raio de 20cm\n");
								break;
							case 1:
								bd.getRobot().CurvarEsquerda(20, 90);
								gui.getConsola().append("Fugir - Curva para a esquerda de 90 graus e raio de 20cm\n");
						}
					}
					bd.getRobot().Parar(false);
					app.getS().release();
					pausa.release();
					ESTADO = DORMIR;
					break;
				case DORMIR:
					try {
						Thread.sleep((2 * (long)((Math.PI * 20 * 90)/180)) + 666);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					if(bd.isFugir()) {
						ESTADO = PAUSADO;
					}else {
						ESTADO = BLOQUEADO;
					}
					break;
			}
		}	
	}
	
	public void pausar() {
		pausa.drainPermits();
		ESTADO = PAUSADO;
//		System.out.println("fugir pausado");
	}
	
	public void continuar() {
		pausa.release();
	}
	
	public void setESTADO(int estado) {
		ESTADO = estado;
	}
}