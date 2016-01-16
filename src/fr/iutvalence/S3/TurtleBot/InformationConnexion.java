package fr.iutvalence.S3.TurtleBot;

public class InformationConnexion
{

	private String adresse;
	
	private int port;
	
	public InformationConnexion(String adresse, int port)
	{
		this.adresse = adresse;
		this.port = port;
	}
	
	public String getAdresse()
	{
		return this.adresse;
	}

	public int getPort()
	{
		return this.port;
	}
}
