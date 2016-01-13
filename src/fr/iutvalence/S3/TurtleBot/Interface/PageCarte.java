package fr.iutvalence.S3.TurtleBot.Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.UIManager;
import java.awt.Font;

public class PageCarte {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

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
		frame.setResizable(false);
		frame.setBounds(100, 100, 287, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Titre
		JLabel lblCarte = new JLabel("- Carte -\r\n");
		lblCarte.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarte.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCarte.setBounds(0, 11, 284, 39);
		frame.getContentPane().add(lblCarte);
		
		//carte
		JLabel lblNewLabel = new JLabel("Carte");
		lblNewLabel.setBounds(10, 59, 264, 352);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(UIManager.getColor("CheckBox.focus"));
	}
}
