package fr.iutvalence.S3.Gopigo;

public class Mouvement 
{	
	private MouvementComposant deplacement;
	
	
	/**
	 * Permet d'initialier les mouvements demandés
	 */
	public Mouvement() 
	{
		this.deplacement = new MouvementComposant(Deplacement.VITESSE_MAX, Deplacement.VITESSE_MIN, Deplacement.PAS, Deplacement.VITESSE_DEPART, Deplacement.CAR);
	}
	
	
	/**
	 * @param sensDeplacement
	 * @param sensRotation
	 * @return une chaine de caractères pour indiquer le mouvement à réaliser 
	 */
	public String obtenirLeDeplacementQuiCorrespondA(Sens_deplacement sensDeplacement)
	{
		String string = "";
		string += sensDeplacement.toString();
		
		return string;
	}

	
	public MouvementComposant obtenirDeplacement(){
		return this.deplacement;
	}
}
