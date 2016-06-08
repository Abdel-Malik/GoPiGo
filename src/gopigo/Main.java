package gopigo;
import interfaceControle.PageChoix;
import interfaceControle.PageControle;
import interfaceControle.PageVisualisation;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class Main 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Application application;
		PageChoix choix = new PageChoix();
		SwingUtilities.invokeLater(choix);
		
		while(choix.getChoix().equals("")){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		switch(choix.getChoix()){
			case "libre":
				PageControle pageControle = new PageControle();
				SwingUtilities.invokeLater(pageControle);
				
				application = new Application(pageControle);
				pageControle.setApplication(application);
				application.creationCommunication();
				application.lireDonneesServeur();
				String  retour = application.obtenirDonneesLues();
				if(!retour.equals(" "))
					JOptionPane.showMessageDialog(pageControle, application,retour+"impossibilité d'utiliser la balise UWB.\nLa localisation durant un commande de positionnement ne fonctionnera pas",JOptionPane.ERROR_MESSAGE);
				application.fonctionner();
			break;
			
			case "simulation":
				PageVisualisation visualisation = new PageVisualisation();
				SwingUtilities.invokeLater(visualisation);
				application = new Application(visualisation);
				visualisation.setApplication(application);
				application.creationCommunicationSimu();
				application.lireDonneesServeur();
				String  ret = application.obtenirDonneesLues();
				if(!ret.isEmpty())
					JOptionPane.showMessageDialog(visualisation, ret+"impossibilité d'utiliser la balise UWB.\nLa localisation durant un commande de positionnement ne fonctionnera pas","message d'erreur", JOptionPane.ERROR_MESSAGE);
				application.fonctionnementAutonome();
			break;
		}

	}
}
