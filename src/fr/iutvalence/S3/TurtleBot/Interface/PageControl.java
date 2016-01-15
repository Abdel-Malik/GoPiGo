package fr.iutvalence.S3.TurtleBot.Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import fr.iutvalence.S3.TurtleBot.Application;
import fr.iutvalence.S3.TurtleBot.Sens_deplacement;
import fr.iutvalence.S3.TurtleBot.Sens_rotation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PageControl {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public PageControl(Application application) 
	{
		initialize(application);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Application application)
	{		
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
		
		JButton deconnexion = new JButton("");
		deconnexion.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/home.png")));
		deconnexion.setBounds(634, 410, 50, 50);
		frame.getContentPane().add(deconnexion);
		
		JButton carte = new JButton("");
		carte.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/Sans titre-2.png")));
		carte.setBounds(10, 385, 75, 75);
		frame.getContentPane().add(carte);
		
		JPanel panelGauche = new JPanel();
		panelGauche.setBounds(0, 61, 341, 410);
		frame.getContentPane().add(panelGauche);
		panelGauche.setLayout(null);
		
		JButton avGauche = new JButton("");
		avGauche.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/hautGauche.png")));
		avGauche.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String str = application.deplacement(Sens_deplacement.AVANT, Sens_rotation.GAUCHE);
				application.envoyerDonnees(str);
			}
		});
		avGauche.setBounds(82, 70, 50, 50);
		panelGauche.add(avGauche);
		
		JButton avancer = new JButton("");
		avancer.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/haut.png")));
		avancer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String str = application.deplacement(Sens_deplacement.AVANT, Sens_rotation.RIEN);
				application.envoyerDonnees(str);
			}
		});
		avancer.setBounds(142, 70, 50, 50);
		panelGauche.add(avancer);
		
		JButton avDroite = new JButton("");
		avDroite.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/hautDroit.png")));
		avDroite.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String str = application.deplacement(Sens_deplacement.AVANT, Sens_rotation.DROITE);
				application.envoyerDonnees(str);
			}
		});
		avDroite.setBounds(202, 70, 50, 50);
		panelGauche.add(avDroite);
		
		JButton gauche = new JButton("");
		gauche.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/gauche.png")));
		gauche.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String str = application.deplacement(Sens_deplacement.RIEN, Sens_rotation.GAUCHE);
				application.envoyerDonnees(str);
			}
		});
		gauche.setBounds(82, 131, 50, 50);
		panelGauche.add(gauche);
		
		JButton stop = new JButton("");
		stop.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/stop.png")));
		stop.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String str = application.deplacement(Sens_deplacement.RIEN, Sens_rotation.RIEN);
				application.envoyerDonnees(str);
			}
		});
		stop.setBounds(142, 131, 50, 50);
		panelGauche.add(stop);
		
		JButton droite = new JButton("");
		droite.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/droite.png")));
		droite.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String str = application.deplacement(Sens_deplacement.RIEN, Sens_rotation.DROITE);
				application.envoyerDonnees(str);
			}
		});
		droite.setBounds(202, 131, 50, 50);
		panelGauche.add(droite);
		
		JButton recGauche = new JButton("");
		recGauche.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/basGauche.png")));
		recGauche.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String str = application.deplacement(Sens_deplacement.ARRIERE, Sens_rotation.GAUCHE);
				application.envoyerDonnees(str);
			}
		});
		recGauche.setBounds(82, 192, 50, 50);
		panelGauche.add(recGauche);
		
		JButton reculer = new JButton("");
		reculer.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/bas.png")));
		reculer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String str = application.deplacement(Sens_deplacement.ARRIERE, Sens_rotation.RIEN);
				application.envoyerDonnees(str);
			}
		});
		reculer.setBounds(142, 192, 50, 50);
		panelGauche.add(reculer);
		
		JButton recDroite = new JButton("");
		recDroite.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/basDroite.png")));
		recDroite.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String str = application.deplacement(Sens_deplacement.ARRIERE, Sens_rotation.DROITE);
				application.envoyerDonnees(str);
			}
		});
		recDroite.setBounds(202, 192, 50, 50);
		panelGauche.add(recDroite);
		
		JLabel lblDplacement = new JLabel("D\u00E9placement");
		lblDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDplacement.setBounds(0, 31, 341, 28);
		panelGauche.add(lblDplacement);
		
		JPanel panelDroit = new JPanel();
		panelDroit.setBounds(345, 61, 349, 410);
		frame.getContentPane().add(panelDroit);
		panelDroit.setLayout(null);
		
		JLabel lblVitesseDplacement = new JLabel("Vitesse de D\u00E9placement");
		lblVitesseDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitesseDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitesseDplacement.setBounds(0, 30, 349, 39);
		panelDroit.add(lblVitesseDplacement);
		
		JProgressBar progressBarVitDep = new JProgressBar();
		progressBarVitDep.setBounds(114, 100, 165, 14);
		panelDroit.add(progressBarVitDep);
		
		JButton buttonMinusDep = new JButton("");
		buttonMinusDep.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				application.envoyerDonnees(",");
			}
		});
		buttonMinusDep.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/moins4.png")));
		buttonMinusDep.setBounds(42, 80, 50, 50);
		panelDroit.add(buttonMinusDep);
		
		JButton buttonPlusDep = new JButton("");
		buttonPlusDep.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				application.envoyerDonnees(";");
			}
		});
		buttonPlusDep.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/plus2.png")));
		buttonPlusDep.setBounds(289, 80, 50, 50);
		panelDroit.add(buttonPlusDep);
		
		JLabel lblVitesseRotation = new JLabel(" Vitesse de Rotation");
		lblVitesseRotation.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitesseRotation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitesseRotation.setBounds(0, 208, 349, 39);
		panelDroit.add(lblVitesseRotation);
		
		JProgressBar progressBarVitRot = new JProgressBar();
		progressBarVitRot.setBounds(114, 278, 165, 14);
		panelDroit.add(progressBarVitRot);
		
		JButton buttonMinusRot = new JButton("");
		buttonMinusRot.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				application.envoyerDonnees(":");
			}
		});
		buttonMinusRot.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/moins4.png")));
		buttonMinusRot.setBounds(42, 258, 50, 50);
		panelDroit.add(buttonMinusRot);
		
		JButton buttonPlusRot = new JButton("");
		buttonPlusRot.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				application.envoyerDonnees("!");
			}
		});
		buttonPlusRot.setIcon(new ImageIcon(PageControl.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/plus2.png")));
		buttonPlusRot.setBounds(289, 258, 50, 50);
		panelDroit.add(buttonPlusRot);
		
		frame.setVisible(true);
		panelGauche.setVisible(true);
		panelDroit.setVisible(true);
	}
}
