package fr.iutvalence.S3.TurtleBot.Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PagePrincipale {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagePrincipale window = new PagePrincipale();
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
	public PagePrincipale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 603, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Titre
		JLabel lblContrleDunRobot = new JLabel("- Contr\u00F4le d'un robot TurtleBot -");
		lblContrleDunRobot.setBounds(0, 0, 597, 33);
		lblContrleDunRobot.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblContrleDunRobot);
		
		//bouton modifier vitesse
		JButton btnNewButton = new JButton("Modifier la vitesse du Robot");
		btnNewButton.setBounds(32, 238, 300, 51);
		frame.getContentPane().add(btnNewButton);
		
		//bouton déplacer robot
		JButton btnNewButton_1 = new JButton("D\u00E9placer le robot\r\n");
		btnNewButton_1.setBounds(32, 108, 300, 51);
		frame.getContentPane().add(btnNewButton_1);
		
		//bouton carte
		JButton btnNewButton_2 = new JButton("\r\n");
		btnNewButton_2.setBounds(440, 44, 111, 75);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(PagePrincipale.class.getResource("/fr/iutvalence/S3/TurtleBot/Ic\u00F4nes/Sans titre-2.png")));
		JLabel thumb = new JLabel();
		thumb.setLocation(433, 206);
		frame.getContentPane().add(thumb);
		thumb.setSize(130,190);
		thumb.setIcon(new ImageIcon(PagePrincipale.class.getResource("/fr/iutvalence/S3/TurtleBot/Ic\u00F4nes/Sans titre-3.png")));
	}
}
