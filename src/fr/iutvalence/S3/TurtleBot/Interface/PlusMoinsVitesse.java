package fr.iutvalence.S3.TurtleBot.Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PlusMoinsVitesse {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlusMoinsVitesse window = new PlusMoinsVitesse();
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
	public PlusMoinsVitesse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 420, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitre = new JLabel("- Titre -");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitre.setBounds(0, 0, 414, 39);
		frame.getContentPane().add(lblTitre);
	}

}
