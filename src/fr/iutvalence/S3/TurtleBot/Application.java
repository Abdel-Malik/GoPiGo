package fr.iutvalence.S3.TurtleBot;


public class Application {
	
	private Communication_wifi comWifi;
	private Mouvement mouvement;
	
	public Application(Communication_wifi comWifi)
	{
		this.comWifi = comWifi;
		this.mouvement = new Mouvement();
	}
	
	//Methode run dans un jeu
	public void fonctionner()
	{
		//TODO
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
		return this.mouvement.deplacement(dep, rot);
	}
	
}
