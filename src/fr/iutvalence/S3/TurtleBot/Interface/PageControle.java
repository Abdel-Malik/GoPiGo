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
	private JButton rotationGauche;
	private JButton rotationDroite;
	private JButton buttonMinusDep;
	private JButton buttonPlusDep;
	private JButton deconnexion;
	private JButton ultrason;
	private JButton ValidationPosition;
	
	//Zones de texte pour transmettre une position
	private JTextField abscisse; 
	private JTextField ordonnee; 
	//Déclaration des barres de progression
	private JProgressBar progressBarVitDep;
	
	
	private volatile boolean leChoixEstFait;
	
	private String choixUtilisateur;
	private PageConnexion pageConnexion;
	
	private JLabel afficheDistance;
	
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
		this.ultrason = new JButton("");		
		this.ultrason.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/localisation2.png")));
		this.ultrason.setBounds(142, 298, 50, 50);
		panelGauche.add(this.ultrason);
		this.ultrason.addActionListener(this);
		
		
		this.avancer = new JButton("");
		this.avancer.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/haut.png")));
		this.avancer.setBounds(142, 50, 50, 50);
		panelGauche.add(this.avancer);
		this.avancer.addActionListener(this);
		
		this.gauche = new JButton("");
		this.gauche.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/gauche.png")));
		this.gauche.setBounds(82, 111, 50, 50);
		panelGauche.add(this.gauche);
		this.gauche.addActionListener(this);
		
		this.stop = new JButton("");
		this.stop.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/stop.png")));
		this.stop.setBounds(142, 111, 50, 50);
		panelGauche.add(this.stop);
		this.stop.addActionListener(this);
		
		this.droite = new JButton("");
		this.droite.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/droite.png")));
		this.droite.setBounds(202, 111, 50, 50);
		panelGauche.add(this.droite);
		this.droite.addActionListener(this);
		
		this.reculer = new JButton("");
		this.reculer.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/bas.png")));
		this.reculer.setBounds(142, 172, 50, 50);
		panelGauche.add(this.reculer);
		this.reculer.addActionListener(this);
		
		this.rotationGauche = new JButton("");
		this.rotationGauche.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/rotationGauche.png")));
		this.rotationGauche.setBounds(82, 172, 50, 50);
		panelGauche.add(this.rotationGauche);
		this.rotationGauche.addActionListener(this);
		
		this.rotationDroite = new JButton("");
		this.rotationDroite.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/rotationDroite.png")));
		this.rotationDroite.setBounds(202, 172, 50, 50);
		panelGauche.add(this.rotationDroite);
		this.rotationDroite.addActionListener(this);
		
		this.deconnexion = new JButton("");
		deconnexion.setBounds(269, 349, 50, 50);
		panelDroit.add(deconnexion);
		this.deconnexion.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/deconnexion.png")));
		this.deconnexion.addActionListener(this);
		
		this.buttonMinusDep = new JButton("");
		this.buttonMinusDep.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/moins4.png")));
		this.buttonMinusDep.setBounds(10, 60, 50, 50);
		panelDroit.add(buttonMinusDep);
		this.buttonMinusDep.addActionListener(this);
		
		this.buttonPlusDep = new JButton("");
		buttonPlusDep.setIcon(new ImageIcon(PageControle.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/plus2.png")));
		buttonPlusDep.setBounds(245, 60, 50, 50);
		panelDroit.add(buttonPlusDep);
		buttonPlusDep.addActionListener(this);
		
		this.ValidationPosition = new JButton("Valider");
		ValidationPosition.setBounds(130, 340, 80, 35);
		panelDroit.add(ValidationPosition);
		ValidationPosition.addActionListener(this);
		
		//Création des champs de texte
		this.abscisse = new JTextField();
		this.abscisse.setBounds(126, 294, 30, 25);
		panelDroit.add(this.abscisse);
		
		this.ordonnee = new JTextField();
		this.ordonnee.setBounds(182, 294, 30, 25);
		panelDroit.add(this.ordonnee);
		
		
		
		//Création des barres de progression
		this.progressBarVitDep = new JProgressBar();
		this.progressBarVitDep.setBounds(70, 79, 165, 14);
		this.progressBarVitDep.setMaximum(100);
		this.progressBarVitDep.setMinimum(0);
		this.progressBarVitDep.setValue(this.mouvement.obtenirDeplacement().progression());
		panelDroit.add(this.progressBarVitDep);
	
		
		//Création des labels
		this.afficheDistance = new JLabel("");
		this.afficheDistance.setBounds(106, 35, 122, 14);
		panelGauche.add(this.afficheDistance);
		
		JLabel lblSujetDeplacee = new JLabel("- Contr\u00F4le du Robot -");
		lblSujetDeplacee.setHorizontalAlignment(SwingConstants.CENTER);
		lblSujetDeplacee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSujetDeplacee.setBounds(0, 11, 674, 28);
		this.getContentPane().add(lblSujetDeplacee);
		
		JLabel lblDplacement = new JLabel("D\u00E9placement");
		lblDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDplacement.setBounds(8, 11, 320, 28);
		panelGauche.add(lblDplacement);		
		
		JLabel lblVitesseDplacement = new JLabel("Vitesse de D\u00E9placement");
		lblVitesseDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitesseDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitesseDplacement.setBounds(68, 11, 167, 28);
		panelDroit.add(lblVitesseDplacement);
		
		JLabel lblCapteur = new JLabel("Capteur");
		lblCapteur.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapteur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCapteur.setBounds(115, 248, 100, 28);
		panelGauche.add(lblCapteur);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosition.setBounds(115, 248, 100, 28);
		panelDroit.add(lblPosition);
		
		JLabel lblAbscisse = new JLabel("x :");
		lblAbscisse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbscisse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAbscisse.setBounds(64, 290, 100, 28);
		panelDroit.add(lblAbscisse);
		
		JLabel lblOrdonnee = new JLabel("y :");
		lblOrdonnee.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdonnee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOrdonnee.setBounds(122, 290, 100, 28);
		panelDroit.add(lblOrdonnee);
		
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
		else if(e.getSource() == this.rotationGauche)
		{
			this.choixUtilisateur = mouvement.obtenirLeDeplacementQuiCorrespondA(Sens_deplacement.ROTATIONG);
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.rotationDroite)
		{
			this.choixUtilisateur = mouvement.obtenirLeDeplacementQuiCorrespondA(Sens_deplacement.ROTATIOND);
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.buttonMinusDep)
		{
			this.choixUtilisateur = mouvement.obtenirLeDeplacementQuiCorrespondA(Sens_deplacement.MOINS);
			this.progressBarVitDep.setValue(this.mouvement.obtenirDeplacement().progression());
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.buttonPlusDep)
		{
			
			this.choixUtilisateur = mouvement.obtenirLeDeplacementQuiCorrespondA(Sens_deplacement.PLUS);
			this.progressBarVitDep.setValue(this.mouvement.obtenirDeplacement().progression());
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.ValidationPosition)
		{
			this.choixUtilisateur = this.obtenirCoordonnee();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.deconnexion)
		{
			this.choixUtilisateur = "BREAK";
			this.leChoixEstFait = true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}
		else if(e.getSource() == this.ultrason)
		{
			this.afficheDistance.setText("En attente de la position...");
			this.afficheDistance.setHorizontalAlignment(JLabel.CENTER);
			this.choixUtilisateur = "u";
			this.leChoixEstFait = true;
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String obtenirDonneesLues = application.obtenirDonneesLues();
			this.afficheDistance.setText(obtenirDonneesLues);
		}
	}

	private String obtenirCoordonnee() {
		return ("P:"+this.abscisse.getText()+":"+this.ordonnee.getText()+":");
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
