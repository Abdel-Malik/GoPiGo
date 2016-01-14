package fr.iutvalence.S3.TurtleBot;

public enum Sens_rotation
{
	
	GAUCHE ("G"),
	
	DROITE ("D"),
	
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
