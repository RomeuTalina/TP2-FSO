package ig;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private BD bd;
	private JTextArea console;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI(new BD());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI(BD bd) {
		this.bd = bd;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 785);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = textField.getText();
				bd.setNomeRobot(text);
				log("Nome do robot definido como " + text + "\n");
			}
		});
		textField.setBounds(90, 50, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(38, 53, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Frente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bd.isRobotAberto()) {
					bd.getRobot().Reta(50);
					log("O robot vai avançar 50cm.\n");
				}
				else {
					log("Por favor ligue um robot.\n");
				}
			}
		});
		btnNewButton.setBounds(275, 108, 96, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Parar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bd.isRobotAberto()) {
					bd.getRobot().Parar(true);
					log("O robot vai parar\n");
				}
				else {
					log("Por favor ligue um robot.\n");
				}
			}
		});
		btnNewButton_1.setBounds(275, 151, 96, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Trás");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bd.isRobotAberto()) {
					bd.getRobot().Reta(-50);
					log("O robot vai retroceder 50cm.\n");
				}
				else {
					log("Por favor ligue um robot.\n");
				}		
			}
		});
		btnNewButton_2.setBounds(275, 195, 96, 39);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Direita");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bd.isRobotAberto()) {
					bd.getRobot().CurvarDireita(0, 90);
					log("O robot vai virar 90º para a esquerda.\n");
				}
				else {
					log("Por favor ligue um robot.\n");
				}	
			}
		});
		btnNewButton_3.setBounds(377, 151, 96, 39);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Esquerda");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bd.isRobotAberto()) {
					bd.getRobot().CurvarEsquerda(0, 90);
					log("O robot vai virar 90º para a esquerda.\n");
				}
				else {
					log("Por favor ligue um robot.\n");
				}
			}
		});
		btnNewButton_4.setBounds(174, 151, 96, 39);
		contentPane.add(btnNewButton_4);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Abrir Canal Comunicação");
		rdbtnNewRadioButton_1.setBounds(38, 300, 182, 21);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Ativar/Desativar");
		chckbxNewCheckBox.setBounds(372, 299, 146, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel = new JLabel("Ficheiro");
		lblNewLabel.setBounds(38, 382, 83, 13);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(125, 379, 417, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Procurar");
		btnNewButton_5.setBounds(550, 378, 104, 21);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Gravar");
		btnNewButton_6.setBounds(145, 408, 85, 21);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Reproduzir");
		btnNewButton_7.setBounds(377, 411, 130, 21);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Limpar");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.setText("");
			}
		});
		btnNewButton_8.setBounds(166, 674, 104, 30);
		contentPane.add(btnNewButton_8);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Imprimir");
		chckbxNewCheckBox_1.setBounds(403, 679, 133, 21);
		contentPane.add(chckbxNewCheckBox_1);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new TitledBorder(null, "Robot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPane.setBounds(27, 21, 657, 240);
		contentPane.add(layeredPane);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Abrir/Fechar Robot");
		rdbtnNewRadioButton.setBounds(453, 30, 174, 21);
		layeredPane.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bd.isRobotAberto()) {
					bd.getRobot().CloseEV3();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					log("Conexão encerrada com o dispositivo " + bd.getNomeRobot() + ".\n");
					bd.setRobotAberto(false);
				}
				else {
					boolean b = bd.getRobot().OpenEV3(bd.getNomeRobot());
					bd.setRobotAberto(b);
					log("Tentativa de conexão iniciada.\n");
					if(bd.isRobotAberto()) {
						log("Conexão iniciada com o robot " + bd.getNomeRobot());
					}
					else {
						log("Conexão falhada.\n");
					}
				}
				rdbtnNewRadioButton.setSelected(bd.isRobotAberto());
			}
		});
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new TitledBorder(null, "Imitar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPane_1.setBounds(27, 285, 657, 57);
		contentPane.add(layeredPane_1);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setBorder(new TitledBorder(null, "Consola", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPane_2.setBounds(27, 443, 657, 273);
		contentPane.add(layeredPane_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 637, 202);
		layeredPane_2.add(scrollPane);
		
		console = new JTextArea();
		scrollPane.setViewportView(console);
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		layeredPane_3.setBorder(new TitledBorder(null, "Gravar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPane_3.setBounds(27, 360, 657, 80);
		contentPane.add(layeredPane_3);
	}
	
	public void log(String text) {
        SwingUtilities.invokeLater(() -> {
            console.append(text);

            console.setCaretPosition(console.getDocument().getLength());
        });
    }
}
