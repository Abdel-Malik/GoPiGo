package fr.iutvalence.S3.TurtleBot;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication_wifi {

	private String ip_address_robot;
	
	private Socket socket;
	
	public Communication_wifi(String ip){
		this.ip_address_robot=ip;
	}
	
	public boolean seConnecter(){
		try 
		{			
		     this.socket = new Socket(this.ip_address_robot,8080);
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
	
	public void envoyerDonnees(Information info){
		DataOutputStream out;
		try 
		{
			out=new DataOutputStream(socket.getOutputStream());  
		    
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void fermerConnexion(){
		try 
		{
			socket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
