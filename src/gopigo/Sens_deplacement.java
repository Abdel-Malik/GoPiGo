package gopigo;

/**
 * Classe permettant de mettre en place la protocole choisi pour l'avancement du robot
 */
public enum Sens_deplacement 
{	
	AVANT ("f"),
	
	ARRIERE ("b"),
	
	GAUCHE ("l"),
	
	DROITE ("r"),
	
	ROTATIONG ("a"),
	
	ROTATIOND ("e"),

	STOP ("s");
	
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
