package interfaceControle;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import gopigo.Application;
import gopigo.InformationConnexion;
import gopigo.InterfaceEntree;
import gopigo.Ordre_robot;
import gopigo.Sens_deplacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

/**
 * Classe permettant le contr�le du robot
 */
public class PageControle extends JFrame implements ActionListener, InterfaceEntree, Runnable
{
	/*** -- D�claration constantes de la page -- ***/
	public final static int TAILLE_BOUTON = 40;
	private final static String ICON_PATH = "/icones/";
	/*** -- D�claration fonctionnement de la page -- ***/
	private Application application;
	private PageConnexion pageConnexion;
	
	//en lien avec l'utilisateur
	private volatile boolean leChoixEstFait;
	private String choixUtilisateur;
	
	private static final long serialVersionUID = 1L;
	
	/*** -- D�claration des boutons --  ***/
	
	//d�placement
	private JButton avancer;
	private JButton gauche;
	private JButton stop;
	private JButton droite;
	private JButton reculer;
	private JButton rotationGauche;
	private JButton rotationDroite;
	
	//vitesse
	private JButton buttonMinusDep;
	private JButton buttonPlusDep;
	
	//servomoteur
	private JButton rotationServoGauche;
	private JButton positionInitial;
	private JButton rotationServoDroite;
	
	//ultrason
	private JButton ultrason;
	
	//position
	private JButton validationPosition;
	private JButton localisationBouton;
	private JButton restartLocButton;
	
	//autre
	private JButton deconnexion;
	private JButton recuperationTension;
	
	/*** -- D�claration des JTextFields --  ***/
	
	//Donn�es position
	private JTextField abscisse; 
	private JTextField ordonnee; 	
	
	/*** -- Autre -- ***/

	private JLabel afficheDistance;
	private JLabel localisation;
	private JLabel tensionBatterie;
	
	//Cr�ation de l'application
	public PageControle() 
	{
		super("Contr�le du robot");

		initialize();
	}

	/**
	 * Initalisation des composants de la fen�tre
	 */
	private void initialize()
	{		

		this.setResizable(false);
		this.setBounds(100, 100, 700, 500);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		
		//Cr�ation des JPanels
		JPanel panelGauche = new JPanel();
		panelGauche.setBounds(0, 61, 341, 410);
		this.getContentPane().add(panelGauche);
		panelGauche.setLayout(null);
		
		JPanel panelDroit = new JPanel();
		panelDroit.setBounds(365, 61, 329, 410);
		this.getContentPane().add(panelDroit);
		panelDroit.setLayout(null);
		
		/*** -- Initialisation des boutons --  ***/

		//d�placement		
		this.avancer = new JButton("");
		this.avancer.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"haut.png")));
		this.avancer.setBounds(142, 38, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(this.avancer);
		this.avancer.addActionListener(this);
		
		this.gauche = new JButton("");
		this.gauche.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"gauche.png")));
		this.gauche.setBounds(82, 99, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(this.gauche);
		this.gauche.addActionListener(this);
		
		this.droite = new JButton("");
		this.droite.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"droite.png")));
		this.droite.setBounds(202, 99, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(this.droite);
		this.droite.addActionListener(this);
		
		this.stop = new JButton("");
		this.stop.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"stop.png")));
		this.stop.setBounds(142, 99, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(this.stop);
		this.stop.addActionListener(this);
		
		this.reculer = new JButton("");
		this.reculer.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"bas.png")));
		this.reculer.setBounds(142, 160, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(this.reculer);
		this.reculer.addActionListener(this);
		
		this.rotationGauche = new JButton("");
		this.rotationGauche.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"rotationGauche.png")));
		this.rotationGauche.setBounds(82, 160, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(this.rotationGauche);
		this.rotationGauche.addActionListener(this);
		
		this.rotationDroite = new JButton("");
		this.rotationDroite.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"rotationDroite.png")));
		this.rotationDroite.setBounds(202, 160, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(this.rotationDroite);
		this.rotationDroite.addActionListener(this);
		
		//Vitesse
		this.buttonMinusDep = new JButton("");
		this.buttonMinusDep.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"moins4.png")));
		this.buttonMinusDep.setBounds(110, 266, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(this.buttonMinusDep);
		this.buttonMinusDep.addActionListener(this);
		
		this.buttonPlusDep = new JButton("");
		this.buttonPlusDep.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"plus2.png")));
		this.buttonPlusDep.setBounds(174, 266, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(this.buttonPlusDep);
		this.buttonPlusDep.addActionListener(this);
		
		//servomoteur
		this.rotationServoGauche = new JButton("");
		this.rotationServoGauche.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"fleche_gauche.png")));
		this.rotationServoGauche.setBounds(50, 170, TAILLE_BOUTON, TAILLE_BOUTON);
		panelDroit.add(this.rotationServoGauche);
		this.rotationServoGauche.addActionListener(this);
		
		this.positionInitial = new JButton("");
		this.positionInitial.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"axe.png")));
		this.positionInitial.setBounds(115, 170, TAILLE_BOUTON, TAILLE_BOUTON);
		panelDroit.add(this.positionInitial);
		this.positionInitial.addActionListener(this);
		
		this.rotationServoDroite = new JButton("");
		this.rotationServoDroite.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"fleche_droite.png")));
		this.rotationServoDroite.setBounds(180, 170, TAILLE_BOUTON, TAILLE_BOUTON);
		panelDroit.add(this.rotationServoDroite);
		this.rotationServoDroite.addActionListener(this);
		
		//ultrason
		this.ultrason = new JButton("");		
		this.ultrason.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"localisation2.png")));
		this.ultrason.setBounds(115, 50, TAILLE_BOUTON, TAILLE_BOUTON);
		panelDroit.add(this.ultrason);
		this.ultrason.addActionListener(this);
		
		//position
		this.validationPosition = new JButton("Valider");
		this.validationPosition.setFont(new Font("book antiqua", Font.PLAIN, 12));
		this.validationPosition.setBounds(170, 287, 80, 25);
		panelDroit.add(this.validationPosition);
		this.validationPosition.addActionListener(this);
		
		this.localisationBouton = new JButton("Localisation");
		this.localisationBouton.setFont(new Font("book antiqua", Font.PLAIN, 11));
		this.localisationBouton.setBounds(162, 327, 96, 25);
		panelDroit.add(this.localisationBouton);
		this.localisationBouton.addActionListener(this);
		
		this.restartLocButton = new JButton("Reset");
		this.restartLocButton.setFont(new Font("book antiqua", Font.PLAIN, 10));
		this.restartLocButton.setBounds(200, 357, 58, 20);
		panelDroit.add(this.restartLocButton);
		this.restartLocButton.addActionListener(this);
		
		//autre
		this.deconnexion = new JButton("");
		deconnexion.setBounds(288, 370, TAILLE_BOUTON, TAILLE_BOUTON);
		panelDroit.add(deconnexion);
		this.deconnexion.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"deconnexion.png")));
		this.deconnexion.addActionListener(this);
		
		this.recuperationTension = new JButton("");
		recuperationTension.setBounds(174, 340, TAILLE_BOUTON, TAILLE_BOUTON);
		panelGauche.add(recuperationTension);
		this.recuperationTension.setIcon(new ImageIcon(PageControle.class.getResource(ICON_PATH+"tension.png")));
		this.recuperationTension.addActionListener(this);
		

		/*** -- Initialisation de JTextFields --  ***/
		
		//Donn�es position
		this.abscisse = new JTextField();
		this.abscisse.setBounds(63, 287, 30, 25);
		panelDroit.add(this.abscisse);
		
		this.ordonnee = new JTextField();
		this.ordonnee.setBounds(125, 287, 30, 25);
		panelDroit.add(this.ordonnee);
	
		/*** -- Cr�ation des labels --  ***/
		
		this.afficheDistance = new JLabel("_ cm");
		this.afficheDistance.setFont(new Font("book antiqua", Font.PLAIN, 15));
		this.afficheDistance.setBounds(166, 78, 100, 14);
		panelDroit.add(this.afficheDistance);
		
		this.localisation = new JLabel("( _ , _ )");
		this.localisation.setFont(new Font("book antiqua", Font.PLAIN, 14));
		this.localisation.setBounds(72, 326, 100, 25);
		panelDroit.add(this.localisation);
		
		this.tensionBatterie = new JLabel("_V");
		this.tensionBatterie.setFont(new Font("book antiqua", Font.PLAIN, 14));
		this.tensionBatterie.setBounds(125, 356, 500, 25);
		panelGauche.add(this.tensionBatterie);
		
		/*G�n�ral*/
		JLabel lblSujetDeplacee = new JLabel("- Contr\u00F4le du Robot -");
		lblSujetDeplacee.setHorizontalAlignment(SwingConstants.CENTER);
		lblSujetDeplacee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSujetDeplacee.setBounds(0, 11, 674, 28);
		this.getContentPane().add(lblSujetDeplacee);
		
		/*Panel Gauche*/
		JLabel lblDplacement = new JLabel("D\u00E9placement");
		lblDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDplacement.setBounds(11, 11, 300, 28);
		panelGauche.add(lblDplacement);		
		
		JLabel lblVitesseDplacement = new JLabel("Vitesse de D\u00E9placement");
		lblVitesseDplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitesseDplacement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitesseDplacement.setBounds(83, 228, 167, 28);
		panelGauche.add(lblVitesseDplacement);
		
		/*Panel Droit*/
		JLabel lblCapteur = new JLabel("Capteur");
		lblCapteur.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapteur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCapteur.setBounds(89, 11, 100, 28);
		panelDroit.add(lblCapteur);
		
		JLabel lblServo = new JLabel("Servomoteur");
		lblServo.setHorizontalAlignment(SwingConstants.CENTER);
		lblServo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblServo.setBounds(90, 125, 100, 28);
		panelDroit.add(lblServo);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosition.setBounds(90, 242, 100, 28);
		panelDroit.add(lblPosition);
		
		JLabel lblAbscisse = new JLabel("x :");
		lblAbscisse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbscisse.setFont(new Font("book antiqua", Font.PLAIN, 15));
		lblAbscisse.setBounds(0, 285, 100, 28);
		panelDroit.add(lblAbscisse);
		
		JLabel lblOrdonnee = new JLabel("y :");
		lblOrdonnee.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdonnee.setFont(new Font("book antiqua", Font.PLAIN, 15));
		lblOrdonnee.setBounds(63, 285, 100, 28);
		panelDroit.add(lblOrdonnee);
		
		//Rendre visible les fen�tres et les JPanels
		panelGauche.setVisible(true);
		panelDroit.setVisible(true);
	}
	
	@Override
	public void run()
	{
		this.setVisible(true);
	}
	
	
	/* 
	 * Permet de savoir quelle action r�aliser par rapport au bouton que l'on a cliqu�
	 */
	public void actionPerformed(ActionEvent e)
	{
		//d�placement
		if(e.getSource() == this.avancer)
		{
			this.choixUtilisateur = Sens_deplacement.AVANT.toString();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.gauche)
		{
			this.choixUtilisateur = Sens_deplacement.GAUCHE.toString();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.stop)
		{
			this.choixUtilisateur = Sens_deplacement.STOP.toString();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.droite)
		{
			this.choixUtilisateur = Sens_deplacement.DROITE.toString();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.reculer)
		{
			this.choixUtilisateur = Sens_deplacement.ARRIERE.toString();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.rotationGauche)
		{
			this.choixUtilisateur = Sens_deplacement.ROTATION_GAUCHE.toString();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.rotationDroite)
		{
			this.choixUtilisateur = Sens_deplacement.ROTATION_DROITE.toString();
			this.leChoixEstFait = true;
		}
		
		//vitesse de d�placement
		else if(e.getSource() == this.buttonMinusDep)
		{
			this.choixUtilisateur = Ordre_robot.VITESSE_MOINS.toString();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.buttonPlusDep)
		{
			
			this.choixUtilisateur = Ordre_robot.VITESSE_PLUS.toString();
			this.leChoixEstFait = true;
		}
		
		//ultrason
		else if(e.getSource() == this.ultrason)
		{
			this.afficheDistance.setText("En attente de la position...");
			this.afficheDistance.setHorizontalAlignment(JLabel.CENTER);
			this.choixUtilisateur = Ordre_robot.DISTANCE_OBSTACLE.toString();
			this.leChoixEstFait = true;
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String obtenirDonneesLues = application.obtenirDonneesLues();
			this.afficheDistance.setText((obtenirDonneesLues+" cm"));
		}
		
		//servomoteur
		else if(e.getSource() == this.rotationServoGauche)
		{
			this.choixUtilisateur = Ordre_robot.TOURNER_SERVO_G.toString();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.positionInitial)
		{
			this.choixUtilisateur = Ordre_robot.POS_SERVO_AXE.toString();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.rotationServoDroite)
		{
			this.choixUtilisateur = Ordre_robot.TOURNER_SERVO_G.toString();
			this.leChoixEstFait = true;
		}
		
		//position
		else if(e.getSource() == this.validationPosition)
		{
			this.choixUtilisateur = this.envoyerCoordonnees();
			this.leChoixEstFait = true;
		}
		else if(e.getSource() == this.localisationBouton)
		{
			this.choixUtilisateur = Ordre_robot.DEMANDE_POSITION.toString();
			this.leChoixEstFait = true;
			
			this.localisation.setText(donneesDePose());
		}
		
		else if(e.getSource() == this.restartLocButton)
		{
			this.choixUtilisateur = Ordre_robot.REINITIALISATION_POSITION.toString();
			this.leChoixEstFait = true;
			this.localisation.setText(donneesDePose());
		}
		
		else if(e.getSource() == this.recuperationTension)
		{
			this.choixUtilisateur = Ordre_robot.DEMANDE_TENSION.toString();
			this.leChoixEstFait = true;
			
			this.tensionBatterie.setText((lireDonnees()+"V"));
		}
		
		
		else if(e.getSource() == this.deconnexion)
		{
			this.choixUtilisateur = Ordre_robot.BREAK.toString();
			this.leChoixEstFait = true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}
		
	}

	/**
	 * Attend un certain temps avant de r�cup�rer des donn�es transmis par le wi-fi
	 * @return la chaine de caract�res re�ue
	 */
	private String lireDonnees() {
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return application.obtenirDonneesLues();
	}

	/**
	 * Structure une chaine pour un affichage de pose
	 * @return la chaine compl�t�e
	 */
	private String donneesDePose(){
		return ("("+lireDonnees()+"�)");
	}
	
	
	/**
	 * Structure l'envoi d'une position � atteindre
	 * @return la chaine de caract�res structur�e
	 */
	private String envoyerCoordonnees() {
		return (Ordre_robot.POSITION.toString()+":"+this.abscisse.getText()+":"+this.ordonnee.getText()+":");
	}

	
	@Override
	public InformationConnexion demandeInformationsConnexion()
	{
		this.pageConnexion = new PageConnexion(this);
		InformationConnexion info = pageConnexion.lanceDialogue();
		
		return info;
	}

	
	/* 
	 * Tant que l'utilisateur n'a pas cliqu� sur un bouton, l'interface attend puis fait passer le bool�en � true 
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
	 * Permet de donner l'application cr�er � la classe qui appelle cette fonction
	 * @param application2
	 */
	public void setApplication(Application application2) {
		this.application = application2;
	}

	@Override
	public void nouvelleInfo(String information) {
		// TODO Auto-generated method stub
		
	}
}
