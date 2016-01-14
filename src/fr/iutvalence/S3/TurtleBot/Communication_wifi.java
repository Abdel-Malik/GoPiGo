package fr.iutvalence.S3.TurtleBot;
import java.io.*;
import java.net.*;

public class Communication_wifi {

	private String ip_address_robot;
	private int port;
	private String character;
	private String modifiedCharacter;
	
	private BufferedReader inFromUser;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;
	
	private Socket socketClient;
	
	public Communication_wifi(String ip, int port) throws IOException
	{
		this.ip_address_robot=ip;
		this.port = port;
	}
	
	public boolean seConnecter()
	{
		try 
		{			
		     this.socketClient = new Socket(this.ip_address_robot, this.port);
		     this.inFromUser = new BufferedReader(new InputStreamReader(System.in));
		     this.outToServer = new DataOutputStream(socketClient.getOutputStream());
		     this.inFromServer = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
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
				this.outToServer.writeBytes(str);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void lireDonneesServeur()
	{
		try {
			this.modifiedCharacter = inFromServer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("FROM SERVER :" + modifiedCharacter);
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
