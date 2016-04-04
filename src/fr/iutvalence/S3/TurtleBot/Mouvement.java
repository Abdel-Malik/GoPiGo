package fr.iutvalence.S3.TurtleBot;

public class Mouvement 
{	
	private MouvementComposant rotation; 
	private MouvementComposant deplacement;
	
	
	/**
	 * Permet d'initialier les mouvements demand�s
	 */
	public Mouvement() 
	{
		this.rotation = new MouvementComposant(Rotation.VITESSE_MAX, Rotation.VITESSE_MIN, Rotation.PAS, Rotation.VITESSE_DEPART, Rotation.CAR);
		this.deplacement = new MouvementComposant(Deplacement.VITESSE_MAX, Deplacement.VITESSE_MIN, Deplacement.PAS, Deplacement.VITESSE_DEPART, Deplacement.CAR);
	}
	
	
	/**
	 * @param sensDeplacement
	 * @param sensRotation
	 * @return une chaine de caract�res pour indiquer le mouvement � r�aliser 
	 */
	public String obtenirLeDeplacementQuiCorrespondA(Sens_deplacement sensDeplacement, Sens_rotation sensRotation)
	{
		String string = "";
		string += sensDeplacement.toString();
		string += sensRotation.toString();
		
		return string;
	}

	public MouvementComposant obtenirRotation(){
		return this.rotation;
	}
	
	public MouvementComposant obtenirDeplacement(){
		return this.deplacement;
	}
}
