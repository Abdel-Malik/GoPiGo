package fr.iutvalence.S3.TurtleBot.Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class PageControle {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageControle window = new PageControle();
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
	public PageControle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDplacementDu = new JLabel("- Contr\u00F4le du Robot -");
		lblDplacementDu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDplacementDu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDplacementDu.setBounds(0, 11, 694, 39);
		frame.getContentPane().add(lblDplacementDu);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/home.png")));
		button.setBounds(634, 410, 50, 50);
		frame.getContentPane().add(button);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/Sans titre-2.png")));
		btnNewButton_5.setBounds(10, 385, 75, 75);
		frame.getContentPane().add(btnNewButton_5);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 61, 341, 410);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton avGauche = new JButton("");
		avGauche.setBounds(82, 70, 50, 50);
		panel.add(avGauche);
		
		JButton avancer = new JButton("");
		avancer.setBounds(142, 70, 50, 50);
		panel.add(avancer);
		
		JButton avDroite = new JButton("");
		avDroite.setBounds(202, 70, 50, 50);
		panel.add(avDroite);
		
		JButton gauche = new JButton("");
		gauche.setBounds(82, 131, 50, 50);
		panel.add(gauche);
		
		JButton stop = new JButton("");
		stop.setBounds(142, 131, 50, 50);
		panel.add(stop);
		
		JButton droite = new JButton("");
		droite.setBounds(202, 131, 50, 50);
		panel.add(droite);
		
		JButton recGauche = new JButton("");
		recGauche.setBounds(82, 192, 50, 50);
		panel.add(recGauche);
		
		JButton reculer = new JButton("");
		reculer.setBounds(142, 192, 50, 50);
		panel.add(reculer);
		
		JButton recDroite = new JButton("");
		recDroite.setBounds(202, 192, 50, 50);
		panel.add(recDroite);
		
		JLabel lblDplacement = new JLabel("D\u00E9placement");
		lblDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDplacement.setBounds(0, 31, 341, 28);
		panel.add(lblDplacement);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(345, 61, 349, 410);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblVitesseDplacement = new JLabel("Vitesse de D\u00E9placement");
		lblVitesseDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitesseDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitesseDplacement.setBounds(0, 30, 349, 39);
		panel_1.add(lblVitesseDplacement);
		
		JProgressBar progressBarVitDep = new JProgressBar();
		progressBarVitDep.setBounds(114, 100, 136, 14);
		panel_1.add(progressBarVitDep);
		
		JButton buttonMinusDep = new JButton("");
		buttonMinusDep.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/moins4.png")));
		buttonMinusDep.setBounds(42, 80, 50, 50);
		panel_1.add(buttonMinusDep);
		
		JButton buttonPlusDep = new JButton("");
		buttonPlusDep.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/plus2.png")));
		buttonPlusDep.setBounds(289, 80, 50, 50);
		panel_1.add(buttonPlusDep);
		
		JLabel lblVitesseRotation = new JLabel(" Vitesse de Rotation");
		lblVitesseRotation.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitesseRotation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitesseRotation.setBounds(0, 208, 349, 39);
		panel_1.add(lblVitesseRotation);
		
		JProgressBar progressBarVitRot = new JProgressBar();
		progressBarVitRot.setBounds(114, 278, 163, 14);
		panel_1.add(progressBarVitRot);
		
		JButton buttonMinusRot = new JButton("");
		buttonMinusRot.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/moins4.png")));
		buttonMinusRot.setBounds(42, 258, 50, 50);
		panel_1.add(buttonMinusRot);
		
		JButton buttonPlusRot = new JButton("");
		buttonPlusRot.setIcon(new ImageIcon(PlusMoinsVitesse.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/plus2.png")));
		buttonPlusRot.setBounds(289, 258, 50, 50);
		panel_1.add(buttonPlusRot);
	}
}
