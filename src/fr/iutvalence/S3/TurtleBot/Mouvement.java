package fr.iutvalence.S3.TurtleBot;

public class Mouvement 
{	
	private Rotation rot;
	private Deplacement dep;
	
	public Mouvement()
	{
		this.rot = new Rotation();
		this.dep = new Deplacement();
	}
	
	public String deplacement(Sens_deplacement sensDeplacement, Sens_rotation sensRotation)
	{
		String string = new String();
		string+=dep.seDeplacer(sensDeplacement);
		string+=rot.effectuerUneRotation(sensRotation);
		string+="\0";
		return string;
	}
	
	
	
}
