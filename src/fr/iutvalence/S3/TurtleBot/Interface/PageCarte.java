package fr.iutvalence.S3.TurtleBot.Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class PageCarte {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageCarte window = new PageCarte();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PageCarte() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 603, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Titre
		JLabel lblContrleDunRobot = new JLabel("- Contr\u00F4le d'un robot TurtleBot -");
		lblContrleDunRobot.setBounds(0, 0, 587, 33);
		lblContrleDunRobot.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblContrleDunRobot);
		
		//conteneur droit
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(342, 33, 235, 363);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		//image du turtlebot
		JPanel panel = new JPanel();
		panel.setBounds(76, 162, 133, 190);
		JLabel thumb = new JLabel();
		thumb.setSize(133,190);
		thumb.setIcon(new ImageIcon(PageCarte.class.getResource("/fr/iutvalence/S3/TurtleBot/Ic\u00F4nes/Sans titre-3.png")));
		panel.add(thumb);
		panel_4.add(panel);
		
		//bouton carte
		JButton btnNewButton_2 = new JButton("\r\n");
		btnNewButton_2.setIcon(new ImageIcon(PageCarte.class.getResource("/fr/iutvalence/S3/TurtleBot/Ic\u00F4nes/Sans titre-2.png")));
		btnNewButton_2.setBounds(76, 11, 111, 75);
		panel_4.add(btnNewButton_2);
		
		//conteneur gauche
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 98, 339, 224);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		//bouton modifier vitesse
		JButton btnNewButton = new JButton("Modifier la vitesse du Robot");
		btnNewButton.setBounds(29, 162, 300, 51);
		panel_2.add(btnNewButton);
		
		//bouton déplacer robot
		JButton btnNewButton_1 = new JButton("D\u00E9placer le robot\r\n");
		btnNewButton_1.setBounds(29, 11, 300, 51);
		panel_2.add(btnNewButton_1);
	}
}
