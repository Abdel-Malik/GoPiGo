package gopigo;
import java.io.*;
import java.net.*;

public class Communication_wifi {

	private String adresseIpRobot;
	private int port;
	private String chaine;
	
	private DataOutputStream versLeServeur;
	private BufferedInputStream provenanceDuServeur;
	
	private Socket socketClient;
	public Communication_wifi(String ip, int port) throws IOException
	{
		this.adresseIpRobot=ip;
		this.port = port;
	}
	
	
	/**
	 * Fonction appelée dans la classe application dans la fonction etablirConnexion
	 * @return un booléen vrai quand les attributs ont pu être initialisés, faux sinon
	 */
	public boolean seConnecter()
	{
		try 
		{			
		     this.socketClient = new Socket(this.adresseIpRobot, this.port);
		     this.versLeServeur = new DataOutputStream(socketClient.getOutputStream());
		     this.provenanceDuServeur = new BufferedInputStream (socketClient.getInputStream());
		     
		     return true;
		}
		catch (UnknownHostException e) 
		{			
			e.printStackTrace();
			return false;
		}
		catch (IOException e) 
		{			
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Fonction qui permet l'envoi de commandes au serveur
	 * @param str pour obtenir la chaine de caratère à envoyer
	 */
	public void envoyerDonnees(String str)
	{
		try 
		{
				System.out.println(str);
				this.versLeServeur.writeBytes(str);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Fonction permettant de lire les donnees envoyées par le serveur
	 */
	public void lireDonneesServeur()
	{
		try 
		{
			this.chaine = "";
			byte[] data = new byte[80];
			
			int count = this.provenanceDuServeur.read(data);
			System.out.println("nbbytes lus : "+count);
			System.out.println("Donees lues : " + data.toString());
			
			for(int i=0; i < count; i++)
			       this.chaine += (char) data[i];
			System.out.println("chaine lue: " + chaine);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fonction permettant d'obtenir la chaine de caractère lue dans la trame Wi-Fi
	 * @return la chaine de caractere lue
	 */
	public String obtenirDonneesLues()
	{
		return this.chaine;
	}
	
	
	/**
	 * Fonction permettant de fermer la connexion, fermer le socket
	 */
	public void fermerConnexion()
	{
		try 
		{
			this.socketClient.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
