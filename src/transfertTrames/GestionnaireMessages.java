package transfertTrames;


import gopigo.Ordre_robot;
import gopigo.Sens_deplacement;


public class GestionnaireMessages {
	
	private short taille_donnees;
	private short code_fonction;
	private short code_sous_fonction;
	public String contenu;
	public String affichage;
	private short checksum;
	
	
	public GestionnaireMessages(){
		this.taille_donnees = 0;
		this.code_fonction = 0;
		this.code_sous_fonction = 0;
		this.contenu = "";
		this.affichage = "";
		this.checksum = 0;
	}
	
	
	/**
	 * méthode récupérant les différentes parties de la trame pour un traitement
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
			this.code_fonction = StructureTrame.NON_STRUCTUREE.getValue();
			this.code_sous_fonction = StructureTrame.NON_STRUCTUREE.getValue();
			this.contenu = message;
			this.affichage = "";
			succes = true;
			return succes;
		}
		this.checksum = recuperationChecksum(message);
		if(testChecksum(message)){
			this.taille_donnees = recuperationTailleDonnees(message);
			this.code_fonction = recuperationCodeFonction(message);
			this.code_sous_fonction = recuperationCodeSousFonction(message);
			this.contenu = recuperationContenu(message);
			this.affichage = "";
			succes = true;
		}
		return succes;
	}

/***********************************************************/
/****** Récupération et vérification des données reçu ******/
/***********************************************************/
	
	
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
		return (short)((extrait.charAt(0)<<8)|(extrait.charAt(1)&0xFF));
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
		return (short)((extrait.charAt(0)<<8)|(extrait.charAt(1)&0xFF));
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
		return (short)extrait.charAt(0);
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
		return (short)extrait.charAt(0);
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
	 * @return un booléen true si la trame semble complete, false sinon
	 */
	private boolean testChecksum(String message) {
		boolean result = true;
		if(this.checksum != calculChecksum())
			result = false;
		return result;
	}
	
	private short calculChecksum(){
		short somme = this.checksum;
		/*short somme = 0;
		**TODO checksum
		*/
		return somme;
	}
	
	/************************************************************/
	/****** Traduction trames =>String || String => trames ******/
	/************************************************************/

	/**
	 * Transforme la trame reçu en un message compréhensible par l'agent
	 * @return un message traduit
	 */
	private String messagePourAgent(){
		String message = "";
		preparationAffichage();
		if((this.code_fonction == ConstructionCode.INITIALISATION.getValue()))
			message = constructionMessageTypeInitialisation();
			
		else if((this.code_fonction == ConstructionCode.INFORMATION.getValue()))
			message = constructionMessageTypeInformation();
			
		else if((this.code_fonction == ConstructionCode.ORDRE.getValue()))
			message = constructionMessageTypeOrdre();
		
		else if((this.code_fonction == ConstructionCode.ENVIRONNEMENT.getValue()))
			message = constructionMessageTypeEnvironnement();
			
		else if(this.code_fonction == ControleGopigo.CODE_FONCTION.getValue())
			message = constructionMessageControleGopico();
		
				
		return (message+"\n");	
	}
	


	/**
	 * Prépare un message avec la trame reçu en un message compréhensible par l'agent pour le code fonction initialisaion
	 * @return un message traduit
	 */
	private String constructionMessageTypeInitialisation() {
		String message;
		message = StructureTrame.SEPARATEUR_ELEMENT.toString()+(Integer.toString(this.code_fonction))+StructureTrame.SEPARATEUR_ELEMENT.toString()+this.contenu.substring(0, this.contenu.indexOf(StructureTrame.SEPARATEUR_ENS_DONNEES.toString()))+StructureTrame.SEPARATEUR_ENS_DONNEES.toString();
		if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.ID.toString() + message;
		else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.POSITION.toString() + message;
		return message;
	}


	/**
	 * Prépare un message avec la trame reçu en un message compréhensible par l'agent pour le code fonction information
	 * @return un message traduit
	 */
	private String constructionMessageTypeInformation() {
		String message;
		message = StructureTrame.SEPARATEUR_ELEMENT.toString()+(Integer.toString(this.code_fonction))+StructureTrame.SEPARATEUR_ENS_DONNEES.toString();
		if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.DEMANDE_ID.toString() + message;
		
		else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.DEMANDE_POSITION.toString() + message;
		
		else if(this.code_sous_fonction == (ConstructionCode.COMPORTEMENT.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.DEMANDE_COMPORTEMENT.toString() + message;
		
		else if(this.code_sous_fonction == (ConstructionCode.VITESSE.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.DEMANDE_VITESSE.toString() + message;
		
		else if(this.code_sous_fonction == (ConstructionCode.VITESSE.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.DEMANDE_VITESSE.toString() + message;
		
		else if(this.code_sous_fonction == (ConstructionCode.TENSION_BATTERIE.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.DEMANDE_TENSION.toString() + message;
		return message;
	}
	


	/**
	 * Prépare un message avec la trame reçu en un message compréhensible par l'agent pour le code fonction ordre
	 * @return un message traduit
	 */
	private String constructionMessageTypeOrdre() {
		String message;
		message = StructureTrame.SEPARATEUR_ELEMENT.toString()+(Integer.toString(this.code_fonction));
		message += StructureTrame.SEPARATEUR_ELEMENT.toString()+this.contenu.substring(0, this.contenu.indexOf(StructureTrame.SEPARATEUR_ENS_DONNEES.toString())+1);
		if(this.code_sous_fonction == (ConstructionCode.ID.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.ID.toString()+message;
		else if(this.code_sous_fonction == (ConstructionCode.POSITION.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.POSITION.toString()+message;
		else if(this.code_sous_fonction == (ConstructionCode.COMPORTEMENT.getValue() | ConstructionCode.ENVOI_MASH.getValue()))
			message = Ordre_robot.COMPORTEMENT.toString()+message;
		return message;
	}
	
	
	/**
	 * Prépare un message avec la trame reçu en un message compréhensible par l'agent pour le code fonction environnement
	 * @return un message traduit
	 */
	private String constructionMessageTypeEnvironnement() {
		String message = "";
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
		return message;
	}
	

	/**
	 * Prépare un message avec la trame reçu en une commande de contrôle compréhensible par l'agent
	 * @return un message traduit
	 */
	private String constructionMessageControleGopico() {
		String message = "";
		if(this.code_sous_fonction == ControleGopigo.AVANCER.getValue())
			message = Sens_deplacement.AVANT.toString();
		else if(this.code_sous_fonction == ControleGopigo.RECULER.getValue())
			message = Sens_deplacement.ARRIERE.toString();
		else if(this.code_sous_fonction == ControleGopigo.GAUCHE.getValue())
			message = Sens_deplacement.GAUCHE.toString();
		else if(this.code_sous_fonction == ControleGopigo.DROITE.getValue())
			message = Sens_deplacement.DROITE.toString();
		else if(this.code_sous_fonction == ControleGopigo.STOP.getValue())
			message = Sens_deplacement.STOP.toString();
		else if(this.code_sous_fonction == ControleGopigo.ROTATION_GAUCHE.getValue())
			message = Sens_deplacement.ROTATION_GAUCHE.toString();
		else if(this.code_sous_fonction == ControleGopigo.ROTATION_DROITE.getValue())
			message = Sens_deplacement.ROTATION_DROITE.toString();
		else if(this.code_sous_fonction == ControleGopigo.ACCELERER.getValue())
			message = Ordre_robot.VITESSE_PLUS.toString();
		else if(this.code_sous_fonction == ControleGopigo.RALENTIR.getValue())
			message = Ordre_robot.VITESSE_MOINS.toString();
		else if(this.code_sous_fonction == ControleGopigo.ACTIONNER_SERVO_GAUCHE.getValue())
			message = Ordre_robot.TOURNER_SERVO_G.toString();
		else if(this.code_sous_fonction == ControleGopigo.ACTINNER_SERVO_DROITE.getValue())
			message = Ordre_robot.TOURNER_SERVO_D.toString();
		else if(this.code_sous_fonction == ControleGopigo.RE_AXEE_SERVO.getValue())
			message = Ordre_robot.POS_SERVO_AXE.toString();

		return message;
	}
	
	
	/**
	 * Transforme la trame reçu en un message compréhensible par le simulateur
	 * Découpage de la chaine de caractère reçu : 
	 * 1er -> Récupération du code fonction (type)
	 * 2e -> Récupération du code sous fonction (sous forme de code ou de commande executé par le robot)
	 * @return un message traduit
	 */
	private String messagePourSimulation(){
		
		String type = "";
		String commande = "";
		
		int indexSeparation =  this.contenu.indexOf(StructureTrame.SEPARATEUR_ELEMENT.toString());
		type = this.contenu.substring(0, indexSeparation);
		
		this.contenu = this.contenu.substring((indexSeparation+1), this.contenu.length());
		
		indexSeparation =  this.contenu.indexOf(StructureTrame.SEPARATEUR_ELEMENT.toString());
		
		if(indexSeparation == -1){
			commande = this.contenu.substring(0, this.contenu.indexOf(StructureTrame.SEPARATEUR_ENS_DONNEES.toString()));
			this.contenu = "";
		}else{
			commande = this.contenu.substring(0, indexSeparation);
			this.contenu = this.contenu.substring((indexSeparation+1), this.contenu.length());
		}		
		determinerInformations(type, commande, this.contenu);
		return creationTrame();
	}
	
	
	/************************************************************/
	/***** Création des codes fonctions et envoi de la trame ****/
	/************************************************************/

	private void determinerInformations(String type, String commande, String message){
		determinerCodes(type, commande, message);
		determinerTailleDonnees(message);
	}

	/**
	 * récupère la taille des données et la sauvegarde
	 * @param message le contenu du message envoyé
	 */
	private void determinerTailleDonnees(String message) {
		this.taille_donnees = (short)(message.length());
	}

	
	/**
	 * récupère les données code_fonction / code_sous_fonction et les sauvegardes
	 * @param type
	 * @param commande
	 * @param message
	 */
	private void determinerCodes(String type, String commande, String message) {
		short type_short = (short)Integer.parseInt(type);
		this.code_fonction = type_short;
		if(message.isEmpty())
			this.code_sous_fonction = ConstructionCode.CONFIRMATION_AGENT.getValue();
		else
			this.code_sous_fonction = ConstructionCode.RETOUR_AGENT.getValue();
	
		if(commande.equals(Ordre_robot.ID.toString())||commande.equals(Ordre_robot.DEMANDE_ID.toString())){
			this.code_sous_fonction = (short)(this.code_sous_fonction | ConstructionCode.ID.getValue());
		}else if(commande.equals(Ordre_robot.POSITION.toString())||commande.equals(Ordre_robot.DEMANDE_POSITION.toString())){
			this.code_sous_fonction = (short)(this.code_sous_fonction | ConstructionCode.POSITION.getValue());
		}else if(commande.equals(Ordre_robot.COMPORTEMENT.toString())||commande.equals(Ordre_robot.DEMANDE_COMPORTEMENT.toString())){
			this.code_sous_fonction = (short)(this.code_sous_fonction | ConstructionCode.COMPORTEMENT.getValue());
		}else if(Integer.parseInt(commande) == (ConstructionCode.PONCTUEL_AGENT.getValue() |ConstructionCode.POSITION.getValue())){
			this.code_sous_fonction = (short)(ConstructionCode.PONCTUEL_AGENT.getValue() |ConstructionCode.POSITION.getValue());
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
			trame[index] = (byte)this.contenu.charAt(i-1);
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
	
	
	
	/*************************************************************************/
	/***** Partie des méthodes étant publique et permettant la traduction ****/
	/*************************************************************************/
	
	
	/**
	 * Methode appellée de l'exterieur pour obtenir une chaine convertit dans le langage de l'autre client 
	 * @return
	 */
	public String obtenirMessageTraduit(){
		String traduit = this.contenu;
		if(this.estStructuree()){
			traduit = this.messagePourAgent();
			//this.code_fonction = StructureTrame.NON_STRUCTUREE.getValue();
			//this.code_sous_fonction = StructureTrame.NON_STRUCTUREE.getValue();
		}else
			traduit = this.messagePourSimulation();
		return traduit;
	}


	public boolean estStructuree(){
		boolean struct = true;
		if( (this.code_fonction == StructureTrame.NON_STRUCTUREE.getValue()) || (this.code_sous_fonction == StructureTrame.NON_STRUCTUREE.getValue()) ){
			struct = false;
		}
		return struct;
	}
	



	@Override
	public String toString(){
		String Classe = "";
		if(this.affichage.isEmpty())
			this.affichage = this.contenu;
		Classe += "taille et contenu : "+this.taille_donnees+" -- "+this.affichage+"\n";
		Classe += "codes f/ss_f/check : "+Integer.toHexString(this.code_fonction)+" "+Integer.toHexString(this.code_sous_fonction)+" "+this.checksum;
		return Classe;
	}
	
	/**
	 * Pour le toString
	 */
	private void preparationAffichage() {
		String fonction = "";
		if(this.code_fonction == ConstructionCode.INITIALISATION.getValue())
			fonction = "Initialisation -";
		else if(this.code_fonction == ConstructionCode.INFORMATION.getValue())
			fonction = "Information -";
		else if(this.code_fonction == ConstructionCode.ORDRE.getValue())
			fonction = "Ordre -";
		else if(this.code_fonction == ConstructionCode.ENVIRONNEMENT.getValue())
			fonction = "Environnement -";
		String sousFonction = "concerne -";
		if(this.code_sous_fonction == ConstructionCode.ID.getValue())
			sousFonction += "ID -";
		if(this.code_sous_fonction == ConstructionCode.POSITION.getValue())
			sousFonction += "Position -";
		else if(this.code_sous_fonction == ConstructionCode.VITESSE.getValue())
			sousFonction = "Vitesse -";
		else if(this.code_sous_fonction == ConstructionCode.COMPORTEMENT.getValue())
			sousFonction = "Comportement -";
		else if(this.code_fonction == ConstructionCode.VOISINAGE.getValue())
			sousFonction = "Voisinage -";
		else if(this.code_fonction == (ConstructionCode.PONCTUEL_AGENT.getValue() | ConstructionCode.POSITION.getValue()))
			sousFonction = "Ponctuel de position -";
		String donnees = this.contenu;
		if(donnees.isEmpty())
			donnees = "demande.";
		if(!fonction.isEmpty())
			this.affichage = fonction + sousFonction + donnees;
	}
}
