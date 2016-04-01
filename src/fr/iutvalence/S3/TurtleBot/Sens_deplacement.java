package fr.iutvalence.S3.TurtleBot;

/**
 * Classe permettant de mettre en place la protocole choisi pour l'avancement du robot
 */
public enum Sens_deplacement 
{	
	AVANT ("A"),
	
	ARRIERE ("R"),

	RIEN ("");
	
	private String deplacement="";
	
	private Sens_deplacement(String deplacement) 
	{
		this.deplacement=deplacement;
	}
	
	@Override
	public String toString() 
	{
		return this.deplacement;
	}
}
