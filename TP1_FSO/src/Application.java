import java.awt.EventQueue;
import java.util.concurrent.Semaphore;

public class Application {
    private GUIRobot gui;
    private Vaguear vaguear;
    private Evitar evitar;
    private Fugir fugir;
    private Semaphore s;

	public Application(GUIRobot gui) {
    	this.gui = gui;
    	this.s = new Semaphore(1);
    	vaguear = new Vaguear(this.gui.getBD(), this, this.gui);
    	evitar = new Evitar(this.gui.getBD(),  this, this.gui);
    	fugir = new Fugir(this.gui.getBD(), this, this.gui);
    	gui.setVaguear(vaguear);
    	gui.setEvitar(evitar);
    	gui.setFugir(fugir);
    	vaguear.start();
    	evitar.start();
    	fugir.start();
    }

    public void run() {
        while(!gui.getBD().isTerminar()) {
        	try {
        		Thread.sleep(10);
        	}catch(InterruptedException e) {	
        		e.printStackTrace();
        	}
           
        }
    }

    public static void main(String[] args) {
    	GUIRobot frame = new GUIRobot();
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setTitle("Trabalho Prático 1 - FSO 2024/25");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        Application app = new Application(frame);
        System.out.println("A aplicação começou.");
        app.run();
        System.out.println("A aplicação terminou.");
    }

	public Semaphore getS() {
		return s;
	}
	
	public Vaguear getVaguear() {
		return vaguear;
	}
	
	public Fugir getFugir() {
		return fugir;
	}
}
