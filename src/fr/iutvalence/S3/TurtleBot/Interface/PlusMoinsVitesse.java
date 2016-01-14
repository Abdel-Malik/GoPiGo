package fr.iutvalence.S3.TurtleBot.Interface;

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
	/*public static void main(String[] args) {
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
	}*/

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
		
		JLabel titre = new JLabel("- Titre -");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titre.setBounds(0, 0, 414, 39);
		frame.getContentPane().add(titre);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(114, 70, 186, 14);
		frame.getContentPane().add(progressBar);
		
		JButton buttonMinus = new JButton("");
		buttonMinus.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/moins4.png")));
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonMinus.setBounds(42, 50, 50, 50);
		frame.getContentPane().add(buttonMinus);
		
		JButton buttonPlus = new JButton("");
		buttonPlus.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/plus2.png")));
		buttonPlus.setBounds(325, 50, 50, 50);
		frame.getContentPane().add(buttonPlus);
		
		JButton buttonHome = new JButton("");
		buttonHome.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/home.png")));
		buttonHome.setBounds(354, 111, 50, 50);
		frame.getContentPane().add(buttonHome);
	}
}
