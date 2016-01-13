package fr.iutvalence.S3.TurtleBot;
import java.io.*;
import java.net.*;

public class Communication_wifi {

	private String ip_address_robot;
	private String character;
	private String modifiedCharacter;
	
	private BufferedReader inFromUser;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;
	
	private Socket socketClient;
	
	public Communication_wifi(String ip) throws IOException
	{
		this.ip_address_robot=ip;
	}
	
	public boolean seConnecter(){
		try 
		{			
		     this.socketClient = new Socket(this.ip_address_robot,8080);
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
	
	public void envoyerDonnees(){
		try 
		{
			while(true)
			{
				this.character = this.inFromUser.readLine();
				if(!(character == null))
					this.outToServer.writeBytes(this.character);
			}
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
	
	public void fermerConnexion(){
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
