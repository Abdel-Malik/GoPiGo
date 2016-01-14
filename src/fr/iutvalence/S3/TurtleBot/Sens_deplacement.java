package fr.iutvalence.S3.TurtleBot;

public enum Sens_deplacement
{	
	AVANT ("Avancer"),
	
	ARRIERE ("Reculer");

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
