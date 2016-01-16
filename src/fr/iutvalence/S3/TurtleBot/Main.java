package fr.iutvalence.S3.TurtleBot;

import java.io.IOException;

import javax.swing.SwingUtilities;

import fr.iutvalence.S3.TurtleBot.Interface.PageControle;


public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		PageControle pageControle = new PageControle();
		SwingUtilities.invokeLater(pageControle);
		
		Application application = new Application(pageControle);
		application.creationCommunication();
		application.fonctionner();
	}
}
