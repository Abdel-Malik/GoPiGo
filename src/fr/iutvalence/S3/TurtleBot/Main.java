package fr.iutvalence.S3.TurtleBot;

import java.io.IOException;

import javax.swing.JFrame;


public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		//Communication_wifi communicationWifi;
		Application application;
			application = new Application(new Communication_wifi("192.168.1.157", 8080));
			application.etablirConnexion();
			application.envoyerDonnees();
			application.lireDonneesServeur();
			application.terminerConnexion();
			
		}
}
