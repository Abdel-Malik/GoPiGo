package fr.iutvalence.S3.TurtleBot;

public class Application {
	
	private Communication_wifi comWifi;
	private InterfaceEntree interfaceEntree;
	private Mouvement mouvement;
	
	private String donneesLues;
	
	public Application(InterfaceEntree interfaceEntree)
	{
		this.mouvement = new Mouvement();
		this.interfaceEntree = interfaceEntree;
		this.donneesLues = new String();
	}
	
	
	/**
	 * Fonction permettant de cr�er la communication entre le serveur et le client
	 */
	public void creationCommunication()
	{
		try
		{
			do
			{
				InformationConnexion info = this.interfaceEntree.demandeInformationsConnexion();
				
				if (info == null)
					System.exit(0);
				
				this.comWifi = new Communication_wifi(info.obtenirAdresse(), info.obtenirPort());
			}while(!this.etablirConnexion());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Fonction qui donne la main � l'utilisateur, c'est cette fonction qui tourne tout au long de l'ex�cution du programme
	 */
	public void fonctionner()
	{
		while (true)
		{
			String choix = this.interfaceEntree.demandeAction();
			if(choix.equals("RECV"))
			{
				choix += '\0';
				this.envoyerDonnees(choix);
				lireDonneesServeur();
			}
			else 
			{
				choix += "\0";
				this.envoyerDonnees(choix);
			}
		}
	}
	
	/**
	 * Fonction appelant la fonction de la classe Communication_Wifi
	 * @return la chaine lue par le client et envoy�e par le serveur
	 */
	public String obtenirDonneesLues()
	{
		return this.comWifi.obtenirDonneesLues();
	}
	
	
	/**
	 * Fonction appelant la foncion seConnecter de la classe Communication_Wifi
	 * @return un booléen, vrai si la connexion est �tablie, faux sinon
	 */
	public boolean etablirConnexion()
	{
		return this.comWifi.seConnecter();
	}
	
	
	/**
	 * Fonction appelant la fonction envoyerDonnees de la classe Communication_Wifi
	 * @param str
	 */
	public void envoyerDonnees(String str)
	{
		this.comWifi.envoyerDonnees(str);
	}
	
	
	/**
	 * Fonction appelant la fonction lireDonneesServeur de la classe Communication_Wifi
	 */
	public void lireDonneesServeur()
	{
		this.comWifi.lireDonneesServeur();
	}
	

	/**
	 * Fonction appelant la fonction fermerConnexin de la classe Communication_Wifi
	 */
	public void terminerConnexion()
	{
		this.comWifi.fermerConnexion();
	}
	
	
	/**
	 * @param dep qui permet de donner le sens de d�placement
	 * @param rot qui permet de donner le sens de rotation
	 * @return une chaine de caract�re contenant le d�placement et la rotation que le robot va effectuer
	 */
	public String deplacement(Sens_deplacement dep, Sens_rotation rot)
	{
		return this.mouvement.obtenirLeDeplacementQuiCorrespondA(dep, rot);
	}	
}
