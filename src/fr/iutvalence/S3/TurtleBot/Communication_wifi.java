package fr.iutvalence.S3.TurtleBot;
import java.io.*;
import java.net.*;

public class Communication_wifi {

	private String adresseIpRobot;
	private int port;
	private String chaine;
	
	private BufferedReader provenanceDuClient;
	private DataOutputStream versLeServeur;
	private BufferedInputStream provenanceDuServeur;
	
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
	
	public void envoyerDonnees(String str)
	{
		try 
		{
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
			this.chaine = "";
			byte[] data = new byte[50];
			
			int count = this.provenanceDuServeur.read(data);
			System.out.println("nbbytes lus : "+count);
			System.out.println("Donees lues :" + data.toString());
			
			for(int i=0; i < data.length; i++)
			       this.chaine += (char) data[i];
			System.out.println("chaine lue: " + chaine);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String obtenirDonneesLues()
	{
		return this.chaine;
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
