package fr.iutvalence.S3.TurtleBot;
/*//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
//import java.net.InetAddress;
//import java.net.Socket;
//import java.net.UnknownHostException;*/

public class Application {
	
	private Communication_wifi comWifi;
	
	public Application(Communication_wifi comWifi){
		this.comWifi = comWifi;
	}
	
	//Methode run dans un jeu
	public void fonctionner(){
		//TODO
	}
	
	public void etablirConnexion(){
		this.comWifi.seConnecter();
	}
	
	public void envoyerDonnees(){
		this.comWifi.envoyerDonnees();
	}
	
	public void lireDonneesServeur(){
		this.comWifi.lireDonneesServeur();
	}
	
	public void montrerCarte(Position p){
		//TODO
	}
	
	public void terminerConnexion()
	{
		this.comWifi.fermerConnexion();
	}
	
}
