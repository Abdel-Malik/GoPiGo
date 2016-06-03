package gopigo;

public interface InterfaceEntree
{
	public InformationConnexion demandeInformationsConnexion(String Serveur);
	
	public String demandeAction();

	void nouvelleInfo(String information);

	public void affichageLoc(String substring);
}
