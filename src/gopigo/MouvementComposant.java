package gopigo;


import javax.swing.JTextField;

public class MouvementComposant 
{	
	private final double vitesseMax;
	private final double vitesseMin;
	private double vitesseActuelle;
	private final double pas;
	
	private final String car;
	
	public MouvementComposant(double vitesseMax, double vitesseMin, double pas, double vitesseActuelle, String car) 
	{
		this.vitesseMax = vitesseMax;
		this.vitesseMin = vitesseMin;
		this.vitesseActuelle = vitesseActuelle;
		this.car = car;
		this.pas = pas;
	}
	
	
	/**
	 * Fonction permettant le fonctionnement des barres de progression de changement de vitesses
	 * @return un entier (le pourcentage)
	 */
	public int progression()
	{
		return (int)((this.vitesseActuelle - this.vitesseMin)/(this.vitesseMax - this.vitesseMin) * 100);
	} 
	
	
	/**
	 * @return une chaine de caractères qui indique de combien on veut augmenter la vitesse
	 */
	public String augmenterVitesse()
	{
		String chaine = "";
		this.vitesseActuelle += this.pas;
		this.vitesseActuelle = ((double)((int)(this.vitesseActuelle * 100))/100);
		if (this.vitesseActuelle <= this.vitesseMax)
		{
			chaine = "VAL"+this.car+Double.toString(this.vitesseActuelle+this.pas);
		}
		else 
		{
			chaine = "VAL"+this.car+Double.toString(this.vitesseMax);
		}
		this.vitesseActuelle = ((double)((int)(this.vitesseActuelle * 100))/100);
		return chaine;
	}
	
	/**
	 * @return une chaine de caracteres qui indique de combien on veut diminuer la vitesse
	 */
	public String diminuerVitesse()
	{
		String chaine = "";
		this.vitesseActuelle -= this.pas;
		this.vitesseActuelle = ((double)((int)(this.vitesseActuelle * 100))/100);
		if (this.vitesseActuelle >= this.vitesseMin)
		{
			chaine = "VAL"+this.car+Double.toString(this.vitesseActuelle-this.pas);
		}
		else 
		{
			chaine = "VAL"+this.car+Double.toString(this.vitesseMin);
		}
		this.vitesseActuelle = ((double)((int)(this.vitesseActuelle * 100))/100);
		return chaine;
	}
	
	
	/**
	 * @return la vitesse actuelle
	 */
	public double obtenirVitesseActuelle()
	{
		return this.vitesseActuelle;
	}
	
	
	/**
	 * @param pourcentageVitesse
	 * @return la vitesse en pourcentage
	 */
	public String convertirPourcentageVitesse(JTextField pourcentageVitesse)
	{
		String texteBouton = pourcentageVitesse.getText();
		Double vitesse;
		int pourcentage = Integer.parseInt(texteBouton);
		if (pourcentage < 0 || pourcentage > 100)
			return "";
		else
		{
			vitesse = (double)((pourcentage * (this.vitesseMax - this.vitesseMin) / 100) + this.vitesseMin);
			this.vitesseActuelle = vitesse;
			return Double.toString(vitesse);
		}	
	}
}
