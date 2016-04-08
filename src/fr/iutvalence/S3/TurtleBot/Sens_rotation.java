package fr.iutvalence.S3.TurtleBot;


/**
 * Classe permettant de mettre en place la protocole choisi pour la rotation du robot
 */
public enum Sens_rotation
{
	
	GAUCHE ("a"),
	
	DROITE ("d"),
	
	RIEN ("");

	private String rotation="";
	
	private Sens_rotation(String rotation) 
	{
		this.rotation=rotation;
	}
	
	@Override
	public String toString() 
	{
		return this.rotation;
	}
}
