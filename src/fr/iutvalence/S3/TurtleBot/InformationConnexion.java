package fr.iutvalence.S3.TurtleBot;

public class InformationConnexion
{

	private String adresse;
	private int port;
	
	
	/**
	 * @param adresse pour obtenir l'adresse IP entrée dans l'interface
	 * @param port pour obtenir le port entré dans l'interface
	 */
	public InformationConnexion(String adresse, int port)
	{
		this.adresse = adresse;
		this.port = port;
	}
	
	
	/**
	 * Obtenir l'adresse IP depuis d'autres classes
	 * @return l'adresse IP
	 */
	public String obtenirAdresse()
	{
		return this.adresse;
	}

	
	/**
	 * Obtenir le port depuis d'autres classes
	 * @return le port
	 */
	public int obtenirPort()
	{
		return this.port;
	}
}
