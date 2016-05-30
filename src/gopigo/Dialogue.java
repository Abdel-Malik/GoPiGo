package gopigo;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import transfertTrames.GestionnaireMessages;

public class Dialogue extends Thread {
	private Communication_wifi comWifi;
	private InterfaceEntree interfaceVisualisation;
	private GestionnaireMessages traitement;
	private final PipedInputStream lectureInfos;
	private final PipedOutputStream ecritureInfos;
	
	
	/***
	 * Constructeur d'une instance de Dialogue. 
	 * @param visu l'interface sur laquelle sera affiché les messages reçu
	 * @param reseauAssoc la socket lié à l'instance
	 * @param entree permet la réception d'information venant d'un autre thread
	 * @param sortie permet l'envoi d'information à un autre thread
	 */
	public Dialogue(InterfaceEntree visu, Communication_wifi reseauAssoc,PipedInputStream entree, PipedOutputStream sortie){
		this.interfaceVisualisation = visu;
		this.comWifi = reseauAssoc;
		this.lectureInfos = entree;
		this.ecritureInfos = sortie;
		this.traitement = new GestionnaireMessages();
	}
	
	/***
	 * Fonction testant la présence de données sur sa socket et son tube associé.
	 */
	public void run(){
		
		while(true){
			
			this.transfertAutreThread();
			this.aiguillage();
		}
	}

	
	/***
	 * En présence de données sur la socket, celles-ci sont affiché, traité et renvoyé par la pipe. 
	 */
	private void transfertAutreThread(){
		String reception = "";
		
		try {
			if(this.comWifi.isAvailable()){
				this.comWifi.lireDonneesServeur();
				reception = this.comWifi.obtenirDonneesLues();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(reception.length() != 0){
			this.interfaceVisualisation.nouvelleInfo(reception+"\n");
			traitement.setGestionnaireMessages(reception);
		
			for(int i = 0; i < reception.length(); i++){
				try {
					ecritureInfos.write(reception.charAt(i));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				ecritureInfos.write(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * Si des données sont présentes en lecture sur le tube d'entré celles-ci sont transmises à la socket associé à l'instance. 
	 */
	private void aiguillage(){
		int nbBytesLues = 0;
		byte[] receptionTube = new byte[80];
		String envoi = "";		
		try {
			if(this.lectureInfos.available() > 0){
				nbBytesLues = lectureInfos.read(receptionTube);
				
				while(receptionTube[receptionTube.length-1] != 0){
						try {
							receptionTube[receptionTube.length] = (byte)lectureInfos.read();
							nbBytesLues++;
						} catch (IOException e) {
								// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(nbBytesLues > 0){
			for(int i=0; i < nbBytesLues; i++)
				envoi += (char)receptionTube[i];
			this.comWifi.envoyerDonnees(envoi);
		}
	}
}