package fr.iutvalence.S3.TurtleBot.Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ModificationVitesse {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificationVitesse window = new ModificationVitesse();
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
	public ModificationVitesse() {
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
		
		JLabel lblVitesseModifier = new JLabel("- S\u00E9l\u00E9ction de la vitesse \u00E0 modifier -");
		lblVitesseModifier.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitesseModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitesseModifier.setBounds(0, 0, 587, 39);
		frame.getContentPane().add(lblVitesseModifier);
		
		JButton btnNewButton = new JButton("- Vitesse Globale -");
		btnNewButton.setBounds(69, 74, 457, 54);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("- Vitesse Rotation -");
		btnNewButton_1.setBounds(69, 173, 457, 54);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("- Vitesse D\u00E9placement -");
		btnNewButton_2.setBounds(69, 275, 457, 54);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\r\n");
		btnNewButton_3.setIcon(new ImageIcon(ModificationVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Ic\u00F4nes/home.png")));
		btnNewButton_3.setBounds(490, 347, 87, 50);
		frame.getContentPane().add(btnNewButton_3);
	}

}
