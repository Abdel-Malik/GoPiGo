package fr.iutvalence.S3.TurtleBot;
import java.io.*;
import java.net.*;

public class Communication_wifi {

	private String adresseIpRobot;
	private int port;
	private String caractere;
	private String caractereModifie;
	
	private BufferedReader provenanceDuClient;
	private DataOutputStream versLeServeur;
	private BufferedReader provenanceDuServeur;
	
	private Socket socketClient;
	
	public Communication_wifi(String ip, int port) throws IOException
	{
		this.adresseIpRobot=ip;
		this.port = port;
	}
	
	public boolean seConnecter()
	{
		try 
		{			
		     this.socketClient = new Socket(this.adresseIpRobot, this.port);
		     this.provenanceDuClient = new BufferedReader(new InputStreamReader(System.in));
		     this.versLeServeur = new DataOutputStream(socketClient.getOutputStream());
		     this.provenanceDuServeur = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
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
	
	public void envoyerDonnees(String str)
	{
		try 
		{
				//this.character = this.inFromUser.readLine();
				//if(!(str == null))
				this.versLeServeur.writeBytes(str);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void lireDonneesServeur()
	{
		try 
		{
			this.caractereModifie = this.provenanceDuServeur.readLine();
			if(!(this.caractereModifie.equals(null)))
				return;
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Donnees recue par le serveur :" + caractereModifie);
	}
	
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
