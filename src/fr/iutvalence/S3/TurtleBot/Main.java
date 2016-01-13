package fr.iutvalence.S3.TurtleBot;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		//Communication_wifi communicationWifi;
		Application application;
		try {
			application = new Application(new Communication_wifi("192.168.1.157"));
			application.etablirConnexion();
			application.envoyerDonnees();
			application.lireDonneesServeur();
			application.terminerConnexion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
