package gopigo;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Application {
	
	private Communication_wifi comWifi;
	private InterfaceEntree interfaceEntree;
	private Mouvement mouvement;
	
	public Application(InterfaceEntree interfaceEntree)
	{
		this.mouvement = new Mouvement();
		this.interfaceEntree = interfaceEntree;
	}
	
	
	/**
	 * Fonction permettant de créer la communication entre le serveur et le client
	 */
	public void creationCommunication()
	{
		try
		{
			do
			{
				InformationConnexion info = this.interfaceEntree.demandeInformationsConnexion();
				
				if (info == null)
					System.exit(0);
				
				this.comWifi = new Communication_wifi(info.obtenirAdresse(), info.obtenirPort());
			}while(!this.etablirConnexion());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Fonction qui donne la main à l'utilisateur, c'est cette fonction qui tourne tout au long de l'exécution du programme
	 */
	public void fonctionner()
	{
		while (true)
		{
			String choix = this.interfaceEntree.demandeAction();
			choix += '\n';
			this.envoyerDonnees(choix);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.lireDonneesServeur();
		}
	}
	
	public void fonctionnementAutonome() throws IOException {
		PipedOutputStream sortieSimu = new PipedOutputStream();
        PipedInputStream  entreeSimu  = new PipedInputStream(sortieSimu);
        
        PipedOutputStream sortieAgent = new PipedOutputStream();
        PipedInputStream  entreeAgent  = new PipedInputStream(sortieAgent);
		

        Dialogue partieSimulateur = new Dialogue(this.interfaceEntree, this.comWifi, entreeAgent, sortieSimu);
        Dialogue partieAgent = new Dialogue(this.interfaceEntree, this.comWifi, entreeSimu, sortieAgent);

        partieSimulateur.start();
        partieAgent.start();

        while (partieSimulateur.isAlive() && partieAgent.isAlive())
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.exit(1);
	}	
	
	/**
	 * Fonction appelant la fonction de la classe Communication_Wifi
	 * @return la chaine lue par le client et envoyée par le serveur
	 */
	public String obtenirDonneesLues()
	{
		return this.comWifi.obtenirDonneesLues();
	}
	
	
	/**
	 * Fonction appelant la foncion seConnecter de la classe Communication_Wifi
	 * @return un boolÃ©en, vrai si la connexion est établie, faux sinon
	 */
	public boolean etablirConnexion()
	{
		return this.comWifi.seConnecter();
	}
	
	
	/**
	 * Fonction appelant la fonction envoyerDonnees de la classe Communication_Wifi
	 * @param str
	 */
	public void envoyerDonnees(String str)
	{
		this.comWifi.envoyerDonnees(str);
	}
	
	
	/**
	 * Fonction appelant la fonction lireDonneesServeur de la classe Communication_Wifi
	 */
	public void lireDonneesServeur()
	{
		this.comWifi.lireDonneesServeur();
	}
	

	/**
	 * Fonction appelant la fonction fermerConnexin de la classe Communication_Wifi
	 */
	public void terminerConnexion()
	{
		this.comWifi.fermerConnexion();
	}
	
	
	/**
	 * @param dep qui permet de donner le sens de déplacement
	 * @param rot qui permet de donner le sens de rotation
	 * @return une chaine de caractère contenant le déplacement et la rotation que le robot va effectuer
	 */
	public String deplacement(Sens_deplacement dep)
	{
		return this.mouvement.obtenirLeDeplacementQuiCorrespondA(dep);
	}

}
