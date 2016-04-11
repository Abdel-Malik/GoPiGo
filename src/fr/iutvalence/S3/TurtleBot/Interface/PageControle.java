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
import fr.iutvalence.S3.TurtleBot.InformationConnexion;
import fr.iutvalence.S3.TurtleBot.InterfaceEntree;
import fr.iutvalence.S3.TurtleBot.Mouvement;
import fr.iutvalence.S3.TurtleBot.Sens_deplacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

/**
 * Classe permettant le contrôle du robot
 */
public class PageControle extends JFrame implements ActionListener, InterfaceEntree, Runnable
{

	private Mouvement mouvement;
	private Application application;
	
	private static final long serialVersionUID = 1L;
	
	//Déclaration des boutons
	private JButton avancer;
	private JButton gauche;
	private JButton stop;
	private JButton droite;
	private JButton reculer;
	private JButton buttonMinusDep;
	private JButton buttonPlusDep;
	private JButton deconnexion;
	private JButton localisation;
	private JButton boutonVitesseDeplacement;
	
	//Déclaration des barres de progression
	private JProgressBar progressBarVitDep;
	
	
	private volatile boolean leChoixEstFait;
	
	private String choixUtilisateur;
	private JTextField pourcentageDeplacement;
	private PageConnexion pageConnexion;
	
	private JLabel affichePosition;
	
	//Création de l'application
	public PageControle() 
	{
		super("Contrôle du robot");
		this.mouvement = new Mouvement();

		initialize();
	}

	/**
	 * Initalisation des composants de la fenêtre
	 */
	private void initialize()
	{		

		this.setResizable(false);
		this.setBounds(100, 100, 700, 500);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		
		//Création des JPanels
		JPanel panelGauche = new JPanel();
		panelGauche.setBounds(0, 61, 341, 410);
		this.getContentPane().add(panelGauche);
		panelGauche.setLayout(null);
		
		JPanel panelDroit = new JPanel();
		panelDroit.setBounds(365, 61, 329, 410);
		this.getContentPane().add(panelDroit);
		panelDroit.setLayout(null);
		
		//Création des boutons
		this.localisation = new JButton("");		
		this.localisation.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/localisation2.png")));
		this.localisation.setBounds(142, 318, 50, 50);
		panelGauche.add(this.localisation);
		this.localisation.addActionListener(this);
		
		
		this.avancer = new JButton("");
		this.avancer.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/haut.png")));
		this.avancer.setBounds(142, 70, 50, 50);
		panelGauche.add(this.avancer);
		this.avancer.addActionListener(this);
		
		this.gauche = new JButton("");
		this.gauche.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/gauche.png")));
		this.gauche.setBounds(82, 131, 50, 50);
		panelGauche.add(this.gauche);
		this.gauche.addActionListener(this);
		
		this.stop = new JButton("");
		this.stop.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/stop.png")));
		this.stop.setBounds(142, 131, 50, 50);
		panelGauche.add(this.stop);
		this.stop.addActionListener(this);
		
		this.droite = new JButton("");
		this.droite.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/droite.png")));
		this.droite.setBounds(202, 131, 50, 50);
		panelGauche.add(this.droite);
		this.droite.addActionListener(this);
		
		this.reculer = new JButton("");
		this.reculer.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/bas.png")));
		this.reculer.setBounds(142, 192, 50, 50);
		panelGauche.add(this.reculer);
		this.reculer.addActionListener(this);
		
		this.deconnexion = new JButton("");
		deconnexion.setBounds(269, 349, 50, 50);
		panelDroit.add(deconnexion);
		this.deconnexion.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/deconnexion.png")));
		this.deconnexion.addActionListener(this);
		
		this.buttonMinusDep = new JButton("");
		this.buttonMinusDep.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/moins4.png")));
		this.buttonMinusDep.setBounds(10, 80, 50, 50);
		panelDroit.add(buttonMinusDep);
		this.buttonMinusDep.addActionListener(this);
		
		this.buttonPlusDep = new JButton("");
		buttonPlusDep.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/plus2.png")));
		buttonPlusDep.setBounds(245, 80, 50, 50);
		panelDroit.add(buttonPlusDep);
		buttonPlusDep.addActionListener(this);
		
		this.boutonVitesseDeplacement = new JButton("âœ“");
		this.boutonVitesseDeplacement.setBounds(184, 140, 51, 20);
		panelDroit.add(this.boutonVitesseDeplacement);
		this.boutonVitesseDeplacement.addActionListener(this);
		
		//Création des barres de progression
		this.progressBarVitDep = new JProgressBar();
		this.progressBarVitDep.setBounds(70, 99, 165, 14);
		this.progressBarVitDep.setMaximum(100);
		this.progressBarVitDep.setMinimum(0);
		this.progressBarVitDep.setValue(this.mouvement.obtenirDeplacement().progression());
		panelDroit.add(this.progressBarVitDep);
		
		// Création des JTextField
		this.pourcentageDeplacement = new JTextField();
		this.pourcentageDeplacement.setBounds(70, 140, 104, 20);
		panelDroit.add(this.pourcentageDeplacement);
		this.pourcentageDeplacement.setColumns(10);
		
		//Création des labels
		this.affichePosition = new JLabel("");
		this.affichePosition.setBounds(106, 379, 122, 14);
		panelGauche.add(this.affichePosition);
		
		JLabel lblDplacementDu = new JLabel("- Contr\u00F4le du Robot -");
		lblDplacementDu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDplacementDu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDplacementDu.setBounds(0, 11, 694, 39);
		this.getContentPane().add(lblDplacementDu);
		
		JLabel lblDplacement = new JLabel("D\u00E9placement");
		lblDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDplacement.setBounds(0, 31, 341, 28);
		panelGauche.add(lblDplacement);		
		
		JLabel lblVitesseDplacement = new JLabel("Vitesse de D\u00E9placement");
		lblVitesseDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitesseDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitesseDplacement.setBounds(68, 37, 167, 39);
		panelDroit.add(lblVitesseDplacement);
		
		JLabel lblChangerVitesseDeplacement = new JLabel("Entrez un pourcentage");
		lblChangerVitesseDeplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangerVitesseDeplacement.setBounds(70, 124, 165, 14);
		panelDroit.add(lblChangerVitesseDeplacement);
		
		//Rendre visible les fenêtres et les JPanels
		panelGauche.setVisible(true);
		panelDroit.setVisible(true);
	}
	
	@Override
	public void run()
	{
		this.setVisible(true);
	}
	
	
	/* 
	 * Permet de savoir quelle action réaliser par rapport au bouton que l'on a cliqué
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == this.avancer)
		{
			this.choixUtilisateur = mouvement.obtenirLeDeplacementQuiCorrespondA(Sens_deplacement.AVANT);
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.gauche)
		{
			this.choixUtilisateur = mouvement.obtenirLeDeplacementQuiCorrespondA(Sens_deplacement.GAUCHE);
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.stop)
		{
			this.choixUtilisateur = mouvement.obtenirLeDeplacementQuiCorrespondA(Sens_deplacement.STOP);
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.droite)
		{
			this.choixUtilisateur = mouvement.obtenirLeDeplacementQuiCorrespondA(Sens_deplacement.DROITE);
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.reculer)
		{
			this.choixUtilisateur = mouvement.obtenirLeDeplacementQuiCorrespondA(Sens_deplacement.ARRIERE);
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.buttonMinusDep)
		{
			this.choixUtilisateur = mouvement.obtenirDeplacement().diminuerVitesse();
			this.progressBarVitDep.setValue(this.mouvement.obtenirDeplacement().progression());
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.buttonPlusDep)
		{
			
			this.choixUtilisateur = mouvement.obtenirDeplacement().augmenterVitesse();
			this.progressBarVitDep.setValue(this.mouvement.obtenirDeplacement().progression());
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.deconnexion)
		{
			this.choixUtilisateur = "STOP";
			this.leChoixEstFait = true;
			System.exit(0);
		}
		else if(e.getSource() == this.boutonVitesseDeplacement)
		{
			String pourcent = mouvement.obtenirDeplacement().convertirPourcentageVitesse(this.pourcentageDeplacement);
			if(pourcent != "")
			{
				this.choixUtilisateur = "VALL";
				this.choixUtilisateur += pourcent;
				this.progressBarVitDep.setValue(this.mouvement.obtenirDeplacement().progression());
			}
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.localisation)
		{
			this.affichePosition.setText("En attente de la position...");
			this.affichePosition.setHorizontalAlignment(JLabel.CENTER);
			this.choixUtilisateur = "RECV";
			this.leChoixEstFait = true;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String obtenirDonneesLues = application.obtenirDonneesLues();
			this.affichePosition.setText(obtenirDonneesLues);
		}
	}

	@Override
	public InformationConnexion demandeInformationsConnexion()
	{
		this.pageConnexion = new PageConnexion(this);
		InformationConnexion info = pageConnexion.lanceDialogue();
		
		return info;
	}

	
	/* 
	 * Tant que l'utilisateur n'a pas cliqué sur un bouton, l'interface attend puis fait passer le booléen à true 
	 * pour permettre d'obtenir du choix de l'utilisateur
	 * @return le choix de l'utilisateur
	 */
	@Override
	public String demandeAction()
	{
		this.leChoixEstFait = false;
		
		while (!leChoixEstFait) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return this.choixUtilisateur;
	}

	/**
	 * Permet de donner l'application créer à la classe qui appelle cette fonction
	 * @param application2
	 */
	public void setApplication(Application application2) {
		this.application = application2;
	}
}
