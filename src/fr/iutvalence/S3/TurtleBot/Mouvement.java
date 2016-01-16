package fr.iutvalence.S3.TurtleBot;

public class Mouvement 
{	
	
	public static String obtenirLeDeplacementQuiCorrespondA(Sens_deplacement sensDeplacement, Sens_rotation sensRotation)
	{
		String string = new String();
		string += sensDeplacement.toString();
		string += sensRotation.toString();
		string += "\0";
		
		return string;
	}
	
	
	
}
