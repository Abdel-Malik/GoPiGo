package fr.iutvalence.S3.TurtleBot;

import java.io.IOException;

import javax.swing.JFrame;

import fr.iutvalence.S3.TurtleBot.Interface.Connexion;

public class Main 
{
	public static void main(String[] args) 
	{
		//Communication_wifi communicationWifi;
		Application application;
			/*application = new Application(new Communication_wifi("192.168.1.157", 8080));
			application.etablirConnexion();
			application.envoyerDonnees();
			application.lireDonneesServeur();
			application.terminerConnexion();*/
			
			Connexion window = new Connexion();
			window.frame.setVisible(true);
		}
}
