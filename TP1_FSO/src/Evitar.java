import java.util.concurrent.Semaphore;

public class Evitar extends Tarefa{
	
	public Evitar(BD bd, Application app, GUIRobot gui) {
		super(bd, app, gui);
		bloqueio = new Semaphore(0);
	}

	
	public void run() {
		ESTADO = BLOQUEADO;
		while(!bd.isTerminar()) {
			switch (ESTADO){
				case BLOQUEADO:
//					System.out.println(gui.getConsola());
				try {
					bloqueio.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					ESTADO = EXECUTAR;
					break;
				case EXECUTAR:
					if(bd.isVaguear()) {
						app.getVaguear().pausar();
					}
					if(bd.isFugir()) {
						app.getFugir().pausar();
					}
					try {
						app.getS().acquire();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(200);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					if(bd.getRobot().SensorToque(bd.getRobot().S_1) == 1) {
						bd.getRobot().Parar(true);
						gui.getConsola().append("Evitar - O robot parou.\n");
						bd.getRobot().Reta(-15);
						gui.getConsola().append("Evitar - Reta de 15 cm para trás.\n");
						bd.getRobot().CurvarEsquerda(0, 90);
						gui.getConsola().append("Evitar - Curva para a esquerda de 90 graus e raio de 0cmn\n");
						bd.getRobot().Parar(false);
					}
					app.getS().release();
					app.getVaguear().continuar();
					app.getFugir().continuar();
					ESTADO = DORMIR;
					break;
				case DORMIR:
					try {
						Thread.sleep(500 + (long)(((Math.PI/2)/30)*1000) + 50);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					if(bd.isEvitar()) {
						ESTADO = EXECUTAR;
					}else {
						ESTADO = BLOQUEADO;
					}
					break;
			}
		}	
	}
}