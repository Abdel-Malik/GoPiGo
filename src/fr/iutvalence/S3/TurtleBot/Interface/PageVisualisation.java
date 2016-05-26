package fr.iutvalence.S3.TurtleBot.Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.iutvalence.S3.TurtleBot.Application;
import fr.iutvalence.S3.TurtleBot.InformationConnexion;
import fr.iutvalence.S3.TurtleBot.InterfaceEntree;

public class PageVisualisation extends JFrame implements ActionListener, InterfaceEntree, Runnable {
	
	public JTextArea donnees;
	public JScrollBar barre;
	private PageConnexion pageConnexion;
	private Application application;
	
	private static final long serialVersionUID = 1L;

	public PageVisualisation(){
		super("transfert de donn�es");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialize();
	}
	
	private void initialize()
	{
		//Initialisation JFrame
		this.setResizable(false);
		this.setBounds(100, 100, 500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
       
		// Jpanel
		JPanel laPage = new JPanel();
		laPage.setLayout(new BorderLayout()); 
		this.setContentPane(laPage);
		
		// une bo�te verticale englob�e dans un JScrollPane
		JPanel verticalPanel = new JPanel();
		verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
		JScrollPane barrePanel = new JScrollPane(verticalPanel);
		laPage.add(barrePanel);
		
		// on initialise et on rajoute un JTextArea
		this.donnees = new JTextArea();
		this.donnees.setText("");
		this.donnees.setEditable(false);
		verticalPanel.add(this.donnees);
		
		//On fait en sorte que la barre commence � la fin du textArea
		barre = barrePanel.getVerticalScrollBar();
		barre.setValue((int) (barre.getMinimum()+(barre.getMaximum()-barre.getMinimum())));
		laPage.revalidate();
	}

	/*
	 * Methode permettant de conserver la scrollBarre en bas du champ de texte. 
	 */
	public void actualiserPositionBarreDefilement(){
		barre.setValue(barre.getMaximum());
		this.revalidate();
	}


	public void nouvelleInfo(){
		String info = this.application.obtenirDonneesLues();
		if(info.length() == 0){
			application.terminerConnexion();
			System.exit(0);
		}
		info = info.substring(0,info.length()-1);
		this.donnees.setText(this.donnees.getText()+"\n("+info+")");
		this.actualiserPositionBarreDefilement();
	}


	/**
	 * Permet de donner l'application cr�er � la classe qui appelle cette fonction
	 * @param application2
	 */
	public void setApplication(Application application2) {
		this.application = application2;
	}
	
	
	@Override
	public void run() {
		this.setVisible(true);	
	}

	@Override
	public InformationConnexion demandeInformationsConnexion()
	{
		this.pageConnexion = new PageConnexion(this);
		InformationConnexion info = pageConnexion.lanceDialogue();
		
		return info;
	}

	@Override
	public String demandeAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
