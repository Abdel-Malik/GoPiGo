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
	 * @param visu l'interface sur laquelle sera affich� les messages re�u
	 * @param reseauAssoc la socket li� � l'instance
	 * @param entree permet la r�ception d'information venant d'un autre thread
	 * @param sortie permet l'envoi d'information � un autre thread
	 */
	public Dialogue(InterfaceEntree visu, Communication_wifi reseauAssoc,PipedInputStream entree, PipedOutputStream sortie){
		this.interfaceVisualisation = visu;
		this.comWifi = reseauAssoc;
		this.lectureInfos = entree;
		this.ecritureInfos = sortie;
		this.traitement = new GestionnaireMessages();
	}
	
	/***
	 * Fonction testant la pr�sence de donn�es sur sa socket et son tube associ�.
	 */
	public void run(){
		
		while(true){
			
			this.transfertAutreThread();
			this.aiguillage();
		}
	}

	
	/***
	 * En pr�sence de donn�es sur la socket, celles-ci sont affich�, trait� et renvoy� par la pipe. 
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
	 * Si des donn�es sont pr�sentes en lecture sur le tube d'entr� celles-ci sont transmises � la socket associ� � l'instance. 
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