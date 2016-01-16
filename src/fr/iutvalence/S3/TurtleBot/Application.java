package fr.iutvalence.S3.TurtleBot;

import java.io.IOException;

public class Application {
	
	private Communication_wifi comWifi;
	private Mouvement mouvement;
	private InterfaceEntree interfaceEntree;
	
	public Application(InterfaceEntree interfaceEntree)
	{
		//this.comWifi = comWifi;
		this.mouvement = new Mouvement();
		this.interfaceEntree = interfaceEntree;
	}
	
	public void creationCommunication()
	{
		InformationConnexion info = this.interfaceEntree.demandeInformationsConnexion();
		
		if (info == null)
			System.exit(0);
		
		try
		{
			this.comWifi = new Communication_wifi(info.getAdresse(), info.getPort());
			this.etablirConnexion();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Methode run dans un jeu
	public void fonctionner()
	{
		while (true)
		{
			String choix = this.interfaceEntree.demandeAction();
			
			this.envoyerDonnees(choix);
		}
	}
	
	public void etablirConnexion()
	{
		this.comWifi.seConnecter();
	}
	
	public void envoyerDonnees(String str)
	{
		this.comWifi.envoyerDonnees(str);
	}
	
	public void lireDonneesServeur()
	{
		this.comWifi.lireDonneesServeur();
	}
	
	public void montrerCarte(Position p)
	{
		//TODO
	}
	
	public void terminerConnexion()
	{
		this.comWifi.fermerConnexion();
	}
	
	public String deplacement(Sens_deplacement dep, Sens_rotation rot)
	{
		return Mouvement.obtenirLeDeplacementQuiCorrespondA(dep, rot);
	}
	
}
