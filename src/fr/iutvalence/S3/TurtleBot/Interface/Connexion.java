package fr.iutvalence.S3.TurtleBot.Interface;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Connexion {
	
	public JFrame frame;
	
	public Connexion() {
		initialize();
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 603, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelConnexion = new JLabel(" - Connexion -");
		labelConnexion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		labelConnexion.setBounds(0, 11, 597, 39);
		frame.getContentPane().add(labelConnexion);
	}

	
	
}
	