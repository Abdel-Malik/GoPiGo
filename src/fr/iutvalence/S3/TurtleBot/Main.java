package fr.iutvalence.S3.TurtleBot;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		Communication_wifi communicationWifi;
		try {
			communicationWifi = new Communication_wifi("192.168.1.157");
			communicationWifi.seConnecter();
			communicationWifi.envoyerDonnees();
			communicationWifi.lireDonneesServeur();
			communicationWifi.fermerConnexion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
