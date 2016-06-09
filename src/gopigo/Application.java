package gopigo;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import transfertTrames.ConstructionCode;
import transfertTrames.StructureTrame;

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
	 * Fonction permettant de créer la communication entre le serveur et le client
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
	 * Fonction qui ouvre deux connexions wifi en étant client de part et d'autre
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
	 * Fonction qui donne la main à l'utilisateur, c'est cette fonction qui tourne tout au long de l'exécution du programme
	 * 
	 */
	public void fonctionner()
	{
		while (true)
		{
			String choix = this.interfaceEntree.demandeAction();
			choix += '\n';
			if(!choix.equals("\n")){
				this.envoyerDonnees(choix);
			}
			try {
				if(this.comWifiRobot.isAvailable()){
					this.comWifiRobot.lireDonneesServeur();
					String localisation = this.comWifiRobot.obtenirDonneesLues();
					if(localisation.length() > 5){
						if((localisation.substring(0, 5)).equals(Integer.toHexString(ConstructionCode.INFORMATION.getValue())+StructureTrame.SEPARATEUR_ELEMENT.toString()+(Integer.toHexString(ConstructionCode.PONCTUEL_AGENT.getValue()|ConstructionCode.POSITION.getValue()))))
							this.interfaceEntree.affichageLoc(localisation.substring(6, localisation.length()-2));
					}
				}
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
	}
	
	/***
	 * Fonction qui fonctionne seule, elle transfert et traduis des messages d'un côté à l'autre en les affichant
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
				e.printStackTrace();
			}
		}
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        //System.exit(1);
	}	
	
	/**
	 * Fonction appelant la fonction de la classe Communication_Wifi
	 * @return la chaine lue par le client et envoyée par le serveur
	 */
	public String obtenirDonneesLues()
	{
		return this.comWifiRobot.obtenirDonneesLues();
	}
	
	
	/**
	 * Fonction appelant la foncion seConnecter de la classe Communication_Wifi
	 * @return un boolÃ©en, vrai si la connexion est établie, faux sinon
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
	 * @param dep qui permet de donner le sens de déplacement
	 * @param rot qui permet de donner le sens de rotation
	 * @return une chaine de caractère contenant le déplacement et la rotation que le robot va effectuer
	 */
	public String deplacement(Sens_deplacement dep)
	{
		return this.mouvement.obtenirLeDeplacementQuiCorrespondA(dep);
	}

}
