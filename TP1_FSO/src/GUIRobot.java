import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class GUIRobot extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BD bd = new BD();
	private JTextArea textArea;
	private Vaguear vaguear;
	private Evitar evitar;
	private Fugir fugir;


	/**
	 * Create the frame.
	 */
	public GUIRobot() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 263, 420, 147);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		JTextField textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
		        bd.setNomeRobot(text);
		        textArea.append("Nome do dispositivo definido como " + text + ".\n");
			}
		});
		textField.setBounds(197, 44, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("Robot:");
		lblNewLabel.setBounds(144, 41, 67, 25);
		contentPane.add(lblNewLabel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Vaguear");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected()) {
					bd.setVaguear(true);
					vaguear.comecar();
					textArea.append("Modo Vaguear iniciado.\n");
				}else {
					bd.setVaguear(false);
					vaguear.fechar();
					textArea.append("Modo Vaguear finalizado.\n");
				}
			
			}
		});
		chckbxNewCheckBox.setBounds(414, 75, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Evitar");
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox_1.isSelected()) {
					bd.setEvitar(true);
					evitar.comecar();
					textArea.append("Modo Evitar iniciado.\n");
				}else {
					bd.setEvitar(false);
					evitar.fechar();
					textArea.append("Modo Evitar finalizado.\n");
				}
			
			}
		});
	
		chckbxNewCheckBox_1.setBounds(414, 101, 93, 21);
		contentPane.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Fugir");
		chckbxNewCheckBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox_2.isSelected()) {
					bd.setFugir(true);
					fugir.comecar();
					textArea.append("Modo Fugir iniciado.\n");
				}else {
					bd.setFugir(false);
					fugir.fechar();
					textArea.append("Modo Fugir finalizado.\n");
				}
			
			}
		});
		chckbxNewCheckBox_2.setBounds(414, 124, 93, 21);
		contentPane.add(chckbxNewCheckBox_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("On/Off");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bd.getRobotAberto()) {
					bd.getRobot().CloseEV3();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					textArea.append("Conexão encerrada com o dispositivo " + bd.getNomeRobot() + ".\n");
					bd.setRobotAberto(false);
				}
				else {
					boolean b = bd.getRobot().OpenEV3(bd.getNomeRobot());
					bd.setRobotAberto(b);
					textArea.append("Conexão iniciada.\n");
				}
				rdbtnNewRadioButton.setSelected(bd.getRobotAberto());
			}
		});
		
		rdbtnNewRadioButton.setBounds(310, 43, 103, 21);
		contentPane.add(rdbtnNewRadioButton);
		
		JButton btnNewButton = new JButton("Frente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.getRobot().Reta(bd.getDistancia());
				bd.getRobot().Parar(false);
				if(bd.isOnOFF()) {
					textArea.append("O robot deslocar-se-á " + bd.getDistancia() + " centímetros para a frente. \n");
				}
			}
		});
		btnNewButton.setBounds(220, 124, 103, 32);
		btnNewButton.setBackground(Color.GREEN);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Parar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.getRobot().Parar(true);
				if(bd.isOnOFF()) {
					textArea.append("O robot parou.");
				}
			}
		});
		btnNewButton_1.setBounds(220, 158, 103, 32);
		btnNewButton_1.setBackground(Color.RED);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Trás");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.getRobot().Reta(-bd.getDistancia());
				if(bd.isOnOFF()) {
					textArea.append("O robot deslocar-se-á " + bd.getDistancia() + " centímetros para trás.\n");
				}
				bd.getRobot().Parar(false);
			}
		});
		btnNewButton_2.setBounds(220, 189, 103, 32);
		btnNewButton_2.setBackground(Color.YELLOW);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Esquerda");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.getRobot().CurvarEsquerda(bd.getRaio(), bd.getAngulo());
				if(bd.isOnOFF()) {
					textArea.append("O robot virará " + bd.getAngulo() + " graus à esquerda, com um raio de " + bd.getRaio() + " centímetros.\n");
				}
				bd.getRobot().Parar(false);
			}
		});
		btnNewButton_3.setBounds(116, 158, 103, 32);
		btnNewButton_3.setBackground(Color.MAGENTA);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Direita");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.getRobot().CurvarDireita(bd.getRaio(), bd.getAngulo());
				if(bd.isOnOFF()) {
					textArea.append("O robot virará " + bd.getAngulo() + " graus à direita, com um raio de " + bd.getRaio() + " centímetros.\n");
				}
				bd.getRobot().Parar(false);
			}
		});
		btnNewButton_3_1.setBounds(322, 158, 103, 32);
		btnNewButton_3_1.setBackground(Color.BLUE);
		contentPane.add(btnNewButton_3_1);
		
		JLabel lblNewLabel_1 = new JLabel("Consola");
		lblNewLabel_1.setBounds(77, 224, 86, 25);
		contentPane.add(lblNewLabel_1);
		
		JTextField textField_1 = new JTextField();
		bd.setDistancia(50);
		textField_1.setText(""+bd.getDistancia());
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dist = Integer.parseInt(textField_1.getText());
				bd.setDistancia(dist);
				textArea.append("Distância definida como " + bd.getDistancia() + ".\n");
			}
		});
		textField_1.setBounds(58, 76, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextField textField_2 = new JTextField();
		bd.setAngulo(90);
		textField_2.setText(""+bd.getAngulo());
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ang = Integer.parseInt(textField_2.getText());
				bd.setAngulo(ang);
				textArea.append("Ângulo de curva definida " + bd.getAngulo() + ".\n");
			}
		});
		textField_2.setBounds(58, 102, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Distância");
		lblNewLabel_2.setBounds(3, 79, 58, 13);
		contentPane.add(lblNewLabel_2);
		
		JTextField textField_3 = new JTextField();
		bd.setRaio(10);
		textField_3.setText(""+bd.getRaio());
		textField_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rad = Integer.parseInt(textField_3.getText());
				bd.setRaio(rad);
				textArea.append("Raio de curva definida " + bd.getRaio() + ".\n");
			}
		});
		textField_3.setBounds(58, 125, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ângulo");
		lblNewLabel_3.setBounds(3, 105, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Raio");
		lblNewLabel_4.setBounds(3, 128, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		
	}
	
	public BD getBD() {
		return bd;
	}
	public JTextArea getConsola() {
		return textArea;
	}

	public void setVaguear(Vaguear vaguear) {
		this.vaguear = vaguear;
	}

	public void setEvitar(Evitar evitar) {
		this.evitar = evitar;
	}

	public void setFugir(Fugir fugir) {
		this.fugir = fugir;
	}
}
