package fr.iutvalence.S3.TurtleBot;

public class Rotation
{

	public final static double VITESSE_MAX_ROTATION = 2.6;
	
	public final static double VITESSE_MIN_ROTATION = 0;
	
	public Rotation(){
		
	}
	
	public String effectuerUneRotation(Sens_rotation sens)
	{
		String string = new String();
		string+=sens.toString();
		return string;
	}
}
