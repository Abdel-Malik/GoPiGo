package transfertTrames;

import gopigo.Ordre_robot;


public class GestionnaireMessages {

	public final static char SEPARATEUR_ELEMENT = ':';
	public final static char SEPARATEUR_ENS_DONNEES = ';';
	public final static short NON_STRUCTUREE = 0xFF;
	
	private short taille_donnees;
	private short code_fonction;
	private short code_sous_fonction;
	private String contenu;
	private short checksum;
	
	
	public GestionnaireMessages(){
		this.taille_donnees = 0;
		this.code_fonction = 0;
		this.code_sous_fonction = 0;
		this.contenu = "";
		this.checksum = 0;
	}
	
	
	/**
	 * fonction récupérant les différentes parties de la trame pour un traitement
	 *@param String contient une trame (aux propriétés préétablies) reçu sur un port 
	 */
	public boolean setGestionnaireMessages(String message){
		boolean succes = false;
		int entete;
		if(message.length()<2)
			entete = 0;
		else
			entete = (( (int)(message.charAt(0)) ) <<8) | ((int)(message.charAt(1))&0xFF);
		if(entete != (StructureTrame.ENTETE.getValue())){
			this.taille_donnees = (short) message.length();
			this.code_fonction = NON_STRUCTUREE;
			this.code_sous_fonction = NON_STRUCTUREE;
			this.contenu = message;
			succes = true;
			return succes;
		}
		this.checksum = recuperationChecksum(message);
		if(testChecksum(message)){
			this.taille_donnees = recuperationTailleDonnees(message);
			this.code_fonction = recuperationCodeFonction(message);
			this.code_sous_fonction = recuperationCodeSousFonction(message);
			this.contenu = recuperationContenu(message);
			succes = true;
		}
		return succes;
	}


/*** Récupération et vérification des données reçu ***/
	
	/**
	 * Fonction répupérant l'information sur le nombre d'octets contenu dans la partie "Données" de la trame. 
	 * @param message la trame reçu
	 * @return le nombre d'octet du champ "Données" de la trame
	 */
	private short recuperationTailleDonnees(String message) {
		int debut = StructureTrame.TAILLE_ENTETE.getValue();
		int nbOctetsARecuperer = StructureTrame.TAILLE_TAILLE_DONNEES.getValue();
		if(message.length() < debut+nbOctetsARecuperer)
			return -1;
		String extrait = parcoursString(message, debut, nbOctetsARecuperer);
		short somme = (short)((extrait.charAt(0)<<8)|(extrait.charAt(1)&0xFF));
		return somme;
	}

	/**
	 * Fonction récupérant la valeur du checksum. 
	 * @param message la trame reçu
	 * @return la valeur du checkSum de la trame lors de l'envoi
	 */
	private short recuperationChecksum(String message) {
		int debut = message.length()-(StructureTrame.TAILLE_ENQUEUX.getValue()+StructureTrame.TAILLE_CHECKSUM.getValue());
		int nbOctetsARecuperer = StructureTrame.TAILLE_CHECKSUM.getValue();
		if(message.length() < debut+nbOctetsARecuperer)
			return -1;
		String extrait =  parcoursString(message, debut, nbOctetsARecuperer);
		short somme = (short)((extrait.charAt(0)<<8)|(extrait.charAt(1)&0xFF));
		return somme;
	}

	/**
	 * @param message la trame reçu
	 * @return le code fonction contenu dans la trame
	 */
	private short recuperationCodeFonction(String message) {
		int debut = StructureTrame.TAILLE_ENTETE.getValue()+StructureTrame.TAILLE_TAILLE_DONNEES.getValue();
		int nbOctetsARecuperer = StructureTrame.TAILLE_CODE_FONCTION.getValue();
		if(message.length() < debut+nbOctetsARecuperer)
			return -1;
		String extrait = parcoursString(message, debut, nbOctetsARecuperer);
		short somme = (short)extrait.charAt(0);
		return somme;
	}
	
	/**
	 * @param message la trame reçu
	 * @return le code sous fonction contenu dans la trame
	 */
	private short recuperationCodeSousFonction(String message) {
		int debut = StructureTrame.TAILLE_ENTETE.getValue()+StructureTrame.TAILLE_TAILLE_DONNEES.getValue()+StructureTrame.TAILLE_CODE_FONCTION.getValue();
		int nbOctetsARecuperer = StructureTrame.TAILLE_CODE_SOUS_FONCTION.getValue();
		if(message.length() < debut+nbOctetsARecuperer)
			return -1;
		String extrait = parcoursString(message, debut, nbOctetsARecuperer);
		short somme = (short)extrait.charAt(0);
		return somme;
	}



	/**
	 * @param message la trame reçu
	 * @return le contenu du champ "Données" de la trame
	 */
	private String recuperationContenu(String message) {
		int debut = StructureTrame.TAILLE_ENTETE.getValue()+StructureTrame.TAILLE_TAILLE_DONNEES.getValue()+StructureTrame.TAILLE_CODE_FONCTION.getValue()+StructureTrame.TAILLE_CODE_SOUS_FONCTION.getValue();
		int nbOctetsARecuperer = this.taille_donnees;
		if(message.length() < debut+nbOctetsARecuperer)
			return "error";
		return parcoursString(message, debut, nbOctetsARecuperer);
	}

	private String parcoursString(String message, int debut, int nbOctetsARecuperer) {
		String m = "";
		for(int i = debut; i < (debut+nbOctetsARecuperer); i++){
			m += message.charAt(i);
		}
		return m;
	}
	
	/**
	 * Calcul la valeur du checkSUm pour la trame reçu et la compare avec l'information présente dans celle-ci
	 * @param message la trame reçu
	 * @return un booléen true si la trame semble complête, false sinon
	 */
	private boolean testChecksum(String message) {
		// TODO Auto-generated method stub
		return true;
	}
	

	/**
	 * Transforme la trame reçu en un message compréhensible par l'agent
	 * @return un message traduit
	 */
	private String messagePourAgent(){
		String message = "";
		
		if((this.code_fonction == ConstructionCode.INITIALISATION.getValue())){
			message = this.contenu.substring(this.contenu.indexOf(SEPARATEUR_ELEMENT), this.contenu.indexOf(SEPARATEUR_ENS_DONNEES));
			if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = ""+message;
			else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_MASH.getValue())){
				//TODO ajouter le code pour le switch du robot
				message = ""+message;
			}
		}
		
		if((this.code_fonction == ConstructionCode.INFORMATION.getValue())){
			if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = Ordre_robot.DEMANDE_ID.toString();
			else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = Ordre_robot.DEMANDE_POSITION.toString();
			else if(this.code_sous_fonction == (ConstructionCode.COMPORTEMENT.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = Ordre_robot.DEMANDE_COMPORTEMENT.toString();
			else if(this.code_sous_fonction == (ConstructionCode.VITESSE.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = Ordre_robot.DEMANDE_VITESSE.toString();
			else if(this.code_sous_fonction == (0x09 | ConstructionCode.ENVOI_MASH.getValue())) /*Ligne test -- à retirer une fois terminé*/
				//TODO ajouter le code pour le switch du robot
				message = Ordre_robot.DEMANDE_TENSION.toString();
			
		}

		if((this.code_fonction == ConstructionCode.ORDRE.getValue())){
			message = this.contenu.substring(this.contenu.indexOf(SEPARATEUR_ELEMENT), this.contenu.indexOf(SEPARATEUR_ENS_DONNEES));
			if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = ""+message;
			else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = ""+message;
			else if(this.code_sous_fonction == (ConstructionCode.COMPORTEMENT.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = ""+message;
		}
		
		if((this.code_fonction == ConstructionCode.ENVIRONNEMENT.getValue())){
			if(this.code_sous_fonction == (ConstructionCode.VOISINAGE.getValue() | ConstructionCode.ENVOI_AGENT.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = "";
			else if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_AGENT.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = "";
			else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_AGENT.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = "";
			else if(this.code_sous_fonction == (ConstructionCode.COMPORTEMENT.getValue() | ConstructionCode.ENVOI_AGENT.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = "";
			else if(this.code_sous_fonction == (ConstructionCode.VITESSE.getValue() | ConstructionCode.ENVOI_AGENT.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = "";
			else if(this.code_sous_fonction == (ConstructionCode.TYPE_AGENT.getValue() | ConstructionCode.ENVOI_AGENT.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = "";
			else if(this.code_sous_fonction == (ConstructionCode.VOISINAGE.getValue() | ConstructionCode.ENVOI_AGENT.getValue()))
				//TODO ajouter le code pour le switch du robot
				message = "";
			
		}
		
		return (message+"\n");	
	}
	
	
	/**
	 * Transforme la trame reçu en un message compréhensible par le simulateur
	 * @return un message traduit
	 */
	private String messagePourSimulation() {
		// TODO Auto-generated method stub
		return null;
	}



	public String creationMessagePourAgent(){
		return this.messagePourAgent();
	}
	
	public String obtenirMessageTraduit(){
		String traduit = this.contenu;
		if(this.estStructuree())
			traduit = this.messagePourAgent();
		else
			traduit = this.messagePourSimulation();
		return traduit;
	}


	public boolean estStructuree(){
		boolean struct = true;
		if( (this.code_fonction == NON_STRUCTUREE) && (this.code_sous_fonction == NON_STRUCTUREE) ){
			struct = false;
		}
		return struct;
	}
	
	@Override
	public String toString(){
		String Classe = "";
		Classe += "taille données"+this.taille_donnees+"\n";
		Classe += "code fonction"+this.code_fonction+"\n";
		Classe += "code sous fonction"+this.code_sous_fonction+"\n";
		Classe += "checkSum"+this.checksum+"\n";
		Classe += "contenu"+this.contenu;
		return Classe;
	}
}
