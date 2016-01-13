package fr.iutvalence.S3.TurtleBot;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Application {
	
	private Communication_wifi comWifi;
	
	public Application(Communication_wifi comWifi){
		this.comWifi = comWifi;
	}
	
	public void fonctionner(){
		//TODO
	}
	
	private void etablirConnexion(String adresse){
		this.comWifi.seConnecter();
	}
	
	private void envoyerDonnees(Information information){
		this.comWifi.envoyerDonnees(information);
	}
	
	public void montrerCarte(Position p){
		//TODO
	}
	
	public void terminerConnexion()
	{
		this.comWifi.fermerConnexion();
	}
	
}
