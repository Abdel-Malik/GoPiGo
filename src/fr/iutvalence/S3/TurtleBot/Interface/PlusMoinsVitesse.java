package fr.iutvalence.S3.TurtleBot.Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

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
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(114, 70, 186, 14);
		frame.getContentPane().add(progressBar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Ic\u00F4nes/moins4.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(42, 50, 50, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Ic\u00F4nes/plus2.png")));
		button.setBounds(325, 50, 50, 50);
		frame.getContentPane().add(button);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Ic\u00F4nes/home.png")));
		btnNewButton_1.setBounds(354, 111, 50, 50);
		frame.getContentPane().add(btnNewButton_1);
	}
}
