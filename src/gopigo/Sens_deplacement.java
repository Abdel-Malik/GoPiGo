package gopigo;

/**
 * Classe permettant de mettre en place la protocole choisi pour l'avancement du robot
 */
public enum Sens_deplacement 
{	
	AVANT ("a"),
	
	ARRIERE ("b"),
	
	GAUCHE ("g"),
	
	DROITE ("d"),
	
	ROTATION_GAUCHE ("q"),
	
	ROTATION_DROITE ("w"),

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
