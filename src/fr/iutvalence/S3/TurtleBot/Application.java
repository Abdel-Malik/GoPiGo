package fr.iutvalence.S3.TurtleBot;

public class Application {
	
	private Communication_wifi comWifi;
	private InterfaceEntree interfaceEntree;
	private Mouvement mouvement;
	
	public Application(InterfaceEntree interfaceEntree)
	{
		this.mouvement = new Mouvement();
		this.interfaceEntree = interfaceEntree;
	}
	
	public void creationCommunication()
	{
		try
		{
			do
			{
				InformationConnexion info = this.interfaceEntree.demandeInformationsConnexion();
				
				if (info == null)
					System.exit(0);
				
				this.comWifi = new Communication_wifi(info.getAdresse(), info.getPort());
			}while(!this.etablirConnexion());
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
			choix += "\0";
			this.envoyerDonnees(choix);
		}
	}
	
	public boolean etablirConnexion()
	{
		return this.comWifi.seConnecter();
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
		return this.mouvement.obtenirLeDeplacementQuiCorrespondA(dep, rot);
	}
	
}
