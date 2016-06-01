package transfertTrames;


import gopigo.Ordre_robot;


public class GestionnaireMessages {

	public final static char SEPARATEUR_ELEMENT = ':';
	public final static char SEPARATEUR_ENS_DONNEES = ';';
	public final static short INFORMATION_PONCTUELLE = 0x05;
	public final static short NON_STRUCTUREE = 0xFF;
	
	private short taille_donnees;
	private short code_fonction;
	private short code_sous_fonction;
	public String contenu;
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
		int debut = (message.length()-1)-(StructureTrame.TAILLE_ENQUEUX.getValue()+StructureTrame.TAILLE_CHECKSUM.getValue());
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
			message = SEPARATEUR_ELEMENT+(Integer.toString(this.code_fonction))+SEPARATEUR_ELEMENT+this.contenu.substring(0, this.contenu.indexOf(SEPARATEUR_ENS_DONNEES))+SEPARATEUR_ENS_DONNEES;
			if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				message = Ordre_robot.ID.toString() + message;
			else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_MASH.getValue())){
				message = Ordre_robot.POSITION.toString() + message;
			}
		}
		
		if((this.code_fonction == ConstructionCode.INFORMATION.getValue())){
			message = SEPARATEUR_ELEMENT+(Integer.toString(this.code_fonction))+SEPARATEUR_ENS_DONNEES;
			if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				message = Ordre_robot.DEMANDE_ID.toString() + message;
			else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				message = Ordre_robot.DEMANDE_POSITION.toString() + message;
			else if(this.code_sous_fonction == (ConstructionCode.COMPORTEMENT.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				message = Ordre_robot.DEMANDE_COMPORTEMENT.toString() + message;
			else if(this.code_sous_fonction == (ConstructionCode.VITESSE.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				message = Ordre_robot.DEMANDE_VITESSE.toString() + message;
			else if(this.code_sous_fonction == (0x09 | ConstructionCode.ENVOI_MASH.getValue())) /* TODO Ligne test -- à retirer une fois terminé*/
				message = Ordre_robot.DEMANDE_TENSION.toString() + message;
			else if(this.code_sous_fonction == (0x0A | ConstructionCode.ENVOI_MASH.getValue())) /* TODO Ligne test -- à retirer une fois terminé*/
					message = Ordre_robot.REINITIALISATION_POSITION.toString() + message;
			
		}

		if((this.code_fonction == ConstructionCode.ORDRE.getValue())){
			message = SEPARATEUR_ELEMENT+(Integer.toString(this.code_fonction))+SEPARATEUR_ELEMENT+this.contenu.substring(0, this.contenu.indexOf(SEPARATEUR_ENS_DONNEES))+SEPARATEUR_ENS_DONNEES;
			if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				message = Ordre_robot.ID.toString()+message;
			else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				message = Ordre_robot.POSITION.toString()+message;
			else if(this.code_sous_fonction == (ConstructionCode.COMPORTEMENT.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
				message = Ordre_robot.COMPORTEMENT.toString()+message;
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
	private String messagePourSimulation(){
		
		String message = "";
		String type = "";
		String commande = "";
		int separateur =  this.contenu.indexOf(SEPARATEUR_ELEMENT);
		type = this.contenu.substring(0, separateur);
		
		this.contenu = this.contenu.substring((separateur+1), this.contenu.length());
	
		separateur =  this.contenu.indexOf(SEPARATEUR_ELEMENT);
		if(separateur == -1){
			commande = this.contenu.substring(0, this.contenu.indexOf(SEPARATEUR_ENS_DONNEES));
			this.contenu = " ";
		}else{
			commande = this.contenu.substring(0, separateur);
			this.contenu = this.contenu.substring((separateur+1), this.contenu.length());
		}		
		determinerInformations(type, commande, message);
		message = creationTrame();
		return message;
	}



	private void determinerInformations(String type, String commande, String message){
		determinerCodes(type, commande, message);
		determinerTailleDonnees(message);
	}

	/**
	 * récupère la taille des données et la sauvegarde
	 * @param message le contenu du message envoyé
	 */
	private void determinerTailleDonnees(String message) {
		this.taille_donnees = (short) message.length();
	}

	
	/**
	 * récupère les données code_fonction / code_sous_fonction et les sauvegarde
	 * @param type
	 * @param commande
	 * @param message
	 */
	private void determinerCodes(String type, String commande, String message) {
		short type_short = (short)Integer.parseInt(type);
		if(type_short == INFORMATION_PONCTUELLE){
			this.code_fonction = ConstructionCode.INFORMATION.getValue();
			this.code_sous_fonction = ConstructionCode.PONCTUEL_AGENT.getValue();
		}else{
			this.code_fonction = type_short;
			if(message.isEmpty())
				this.code_sous_fonction = ConstructionCode.CONFIRMATION_AGENT.getValue();
			else
				this.code_sous_fonction = ConstructionCode.RETOUR_AGENT.getValue();
		}
		if(commande.equals(Ordre_robot.ID.toString())||commande.equals(Ordre_robot.DEMANDE_ID.toString())){
			this.code_sous_fonction = (short)(this.code_sous_fonction + ConstructionCode.ID.getValue());
		}else if(commande.equals(Ordre_robot.POSITION.toString())||commande.equals(Ordre_robot.DEMANDE_POSITION.toString())){
			this.code_sous_fonction = (short)(this.code_sous_fonction + ConstructionCode.POSITION.getValue());
		}else if(commande.equals(Ordre_robot.COMPORTEMENT.toString())||commande.equals(Ordre_robot.DEMANDE_COMPORTEMENT.toString())){
			this.code_sous_fonction = (short)(this.code_sous_fonction + ConstructionCode.COMPORTEMENT.getValue());
		}
	}



	/**
	 * Se sert des valeurs des attribut pour créer une trame complete.
	 * @return retourne la trame créée
	 */
	private String creationTrame() {
		int taille = (StructureTrame.TAILLE_ENTETE.getValue()+StructureTrame.TAILLE_TAILLE_DONNEES.getValue()+StructureTrame.TAILLE_CODE_FONCTION.getValue()+StructureTrame.TAILLE_CODE_SOUS_FONCTION.getValue()+this.taille_donnees+StructureTrame.TAILLE_CHECKSUM.getValue()+StructureTrame.TAILLE_ENQUEUX.getValue());
		byte[] trame = new byte[taille];
		int index = 0;
		int maxFor = StructureTrame.TAILLE_ENTETE.getValue();
		for(int i = 1; i <= maxFor; i++){
			int decalage = 8*(maxFor-i);
			trame[index] = (byte)((StructureTrame.ENTETE.getValue()&(0x11<<decalage))>>decalage);
					index++;
		}
		maxFor = StructureTrame.TAILLE_TAILLE_DONNEES.getValue();
		for(int i = 1; i <= maxFor; i++){
			int decalage = 8*(maxFor-i);
			trame[index] = (byte)((this.taille_donnees&(0x11<<decalage))>>decalage);
					index++;
		}
		maxFor = StructureTrame.TAILLE_CODE_FONCTION.getValue();
		for(int i = 1; i <= maxFor; i++){
			int decalage = 8*(maxFor-i);
			trame[index] = (byte)((this.code_fonction&(0x11<<decalage))>>decalage);
					index++;
		}
		maxFor = StructureTrame.TAILLE_CODE_SOUS_FONCTION.getValue();
		for(int i = 1; i <= maxFor; i++){
			int decalage = 8*(maxFor-i);
			trame[index] = (byte)((this.code_sous_fonction&(0x11<<decalage))>>decalage);
					index++;
		}
		maxFor = this.taille_donnees;
		for(int i = 1; i <= maxFor; i++){
			trame[index] = (byte)this.contenu.charAt(i);
					index++;
		}
		maxFor = StructureTrame.TAILLE_CHECKSUM.getValue();
		for(int i = 1; i <= maxFor; i++){
			int decalage = 8*(maxFor-i);
			trame[index] = (byte)((this.checksum&(0x11<<decalage))>>decalage);
					index++;
		}
		maxFor = StructureTrame.TAILLE_ENQUEUX.getValue();
		for(int i = 1; i <= maxFor; i++){
			int decalage = 8*(maxFor-i);
			trame[index] = (byte)((StructureTrame.ENQUEUE.getValue()&(0x11<<decalage))>>decalage);
					index++;
		}
		return trame.toString();
	}
	
	/**
	 * Methode appellée de l'exterieur pour obtenir une chaine convertit dans le langage de l'autre client 
	 * @return
	 */
	public String obtenirMessageTraduit(){
		String traduit = this.contenu;
		if(this.estStructuree()){
			traduit = this.messagePourAgent();
			this.code_fonction = NON_STRUCTUREE;
			this.code_sous_fonction = NON_STRUCTUREE;
		}else
			traduit = this.messagePourSimulation();
		return traduit;
	}


	public boolean estStructuree(){
		boolean struct = true;
		if( (this.code_fonction == NON_STRUCTUREE) || (this.code_sous_fonction == NON_STRUCTUREE) ){
			struct = false;
		}
		return struct;
	}
	
	@Override
	public String toString(){
		String Classe = "";
		Classe += "taille et contenu : "+this.taille_donnees+" -- "+this.contenu+"\n";
		Classe += "codes f/ss_f/check : "+Integer.toHexString(this.code_fonction)+" "+Integer.toHexString(this.code_sous_fonction)+" "+this.checksum;
		return Classe;
	}
}
