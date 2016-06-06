package gopigo;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Application {
	
	private Communication_wifi comWifiRobot;
	private Communication_wifi comWifiSimulateur;
	private InterfaceEntree interfaceEntree;
	private Mouvement mouvement;
	
	
	public Application(InterfaceEntree interfaceEntree)
	{
		this.mouvement = new Mouvement();
		this.interfaceEntree = interfaceEntree;
	}
	
	
	
	/**
	 * Fonction permettant de cr�er la communication entre le serveur et le client
	 */
	public void creationCommunication()
	{
		try
		{
			do
			{
				InformationConnexion info = this.interfaceEntree.demandeInformationsConnexion("ROBOT");
				
				if (info == null)
					System.exit(0);
				
				this.comWifiRobot = new Communication_wifi(info.obtenirAdresse(), info.obtenirPort());
			}while(!this.etablirConnexion(this.comWifiRobot));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/***
	 * Fonction qui ouvre deux connexions wifi en �tant client de part et d'autre
	 */
	public void creationCommunicationSimu()
	{
		try
		{
			do
			{
				InformationConnexion infoS = this.interfaceEntree.demandeInformationsConnexion("SIMULATEUR");
				
				if (infoS == null)
					System.exit(0);
				
				this.comWifiSimulateur = new Communication_wifi(infoS.obtenirAdresse(), infoS.obtenirPort());
			}while(!this.etablirConnexion(this.comWifiSimulateur));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			do
			{
				InformationConnexion info = this.interfaceEntree.demandeInformationsConnexion("ROBOT");
				
				if (info == null)
					System.exit(0);
				
				this.comWifiRobot = new Communication_wifi(info.obtenirAdresse(), info.obtenirPort());
			}while(!this.etablirConnexion(this.comWifiRobot));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Fonction qui donne la main � l'utilisateur, c'est cette fonction qui tourne tout au long de l'ex�cution du programme
	 * @throws InterruptedException 
	 */
	public void fonctionner() throws InterruptedException
	{
		while (true)
		{
			String choix = this.interfaceEntree.demandeAction();
			choix += '\n';
			if(!choix.equals("\n")){
				this.envoyerDonnees(choix);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if(this.comWifiRobot.isAvailable()){
					this.comWifiRobot.lireDonneesServeur();
					String localisation = this.comWifiRobot.obtenirDonneesLues();
					if(localisation.length() > 5){
						if((localisation.substring(0, 5)).equals("2:52:"))
							this.interfaceEntree.affichageLoc(localisation.substring(5, localisation.length()-2));
					}
				}
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
	}
	
	/***
	 * Fonction qui fonctionne seule, elle transfert et traduis des messages d'un c�t� � l'autre en les affichant
	 * @throws IOException
	 */
	public void fonctionnementAutonome() throws IOException {
		PipedOutputStream sortieSimu = new PipedOutputStream();
        PipedInputStream  entreeSimu  = new PipedInputStream(sortieSimu);
        
        PipedOutputStream sortieAgent = new PipedOutputStream();
        PipedInputStream  entreeAgent  = new PipedInputStream(sortieAgent);
		

        Dialogue partieSimulateur = new Dialogue(this.interfaceEntree, this.comWifiSimulateur, entreeAgent, sortieSimu);
        Dialogue partieAgent = new Dialogue(this.interfaceEntree, this.comWifiRobot, entreeSimu, sortieAgent);

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
        //System.exit(1);
	}	
	
	/**
	 * Fonction appelant la fonction de la classe Communication_Wifi
	 * @return la chaine lue par le client et envoy�e par le serveur
	 */
	public String obtenirDonneesLues()
	{
		return this.comWifiRobot.obtenirDonneesLues();
	}
	
	
	/**
	 * Fonction appelant la foncion seConnecter de la classe Communication_Wifi
	 * @return un booléen, vrai si la connexion est �tablie, faux sinon
	 */
	public boolean etablirConnexion(Communication_wifi laSocket)
	{
		return laSocket.seConnecter();
	}
	
	
	/**
	 * Fonction appelant la fonction envoyerDonnees de la classe Communication_Wifi
	 * @param str
	 */
	public void envoyerDonnees(String str)
	{
		this.comWifiRobot.envoyerDonnees(str);
	}
	
	
	/**
	 * Fonction appelant la fonction lireDonneesServeur de la classe Communication_Wifi
	 */
	public void lireDonneesServeur()
	{
		this.comWifiRobot.lireDonneesServeur();
	}
	

	/**
	 * Fonction appelant la fonction fermerConnexin de la classe Communication_Wifi
	 */
	public void terminerConnexion()
	{
		this.comWifiRobot.fermerConnexion();
	}
	
	
	/**
	 * @param dep qui permet de donner le sens de d�placement
	 * @param rot qui permet de donner le sens de rotation
	 * @return une chaine de caract�re contenant le d�placement et la rotation que le robot va effectuer
	 */
	public String deplacement(Sens_deplacement dep)
	{
		return this.mouvement.obtenirLeDeplacementQuiCorrespondA(dep);
	}

}
