package fr.iutvalence.S3.TurtleBot;

public class Deplacement 
{
	
	public final static double VITESSE_MAX_DEPLACEMENT = 0.6;
	
	public final static double VITESSE_MIN_DEPLACEMENT = 0.1;
	
	public static double vitesseActuelle;
	
	public Deplacement()
	{
		
	}
	
	public String seDeplacer(Sens_deplacement sens)
	{
		String string = new String();
		string+=sens.toString();
		return string;
	}

}
