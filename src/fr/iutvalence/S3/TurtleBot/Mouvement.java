package fr.iutvalence.S3.TurtleBot;

public class Mouvement 
{	
	private MouvementComposant rotation; 
	private MouvementComposant deplacement;
	
	public Mouvement() 
	{
		this.rotation = new MouvementComposant(Rotation.VITESSE_MAX, Rotation.VITESSE_MIN, Rotation.PAS, Rotation.VITESSE_DEPART, Rotation.CAR_UP, Rotation.CAR_DOWN);
		this.deplacement = new MouvementComposant(Deplacement.VITESSE_MAX, Deplacement.VITESSE_MIN, Deplacement.PAS, Deplacement.VITESSE_DEPART, Deplacement.CAR_UP, Deplacement.CAR_DOWN);
	}
	
	public String obtenirLeDeplacementQuiCorrespondA(Sens_deplacement sensDeplacement, Sens_rotation sensRotation)
	{
		String string = new String();
		string += sensDeplacement.toString();
		string += sensRotation.toString();
		string += "\0";
		
		return string;
	}
	
	public MouvementComposant getRotation(){
		return this.rotation;
	}
	
	public MouvementComposant getDeplacement(){
		return this.deplacement;
	}
}
