package transfertTrames;


public class GestionnaireMessages {

	public final static char SEPARATEUR_ELEMENT = ':';
	public final static char SEPARATEUR_ENS_DONNEES = ';';
	
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
	 *@param String contient une trame au propriété préétablies reçu sur un port 
	 */
	public boolean setGestionnaireMessages(String message){
		boolean succes = false;
		this.checksum = recuperationChecksum(message);
		if(testChecksum(message) != 0){
			succes = true;
			this.taille_donnees = recuperationTailleDonnees(message);
			this.code_fonction = recuperationCodeFonction(message);
			this.code_sous_fonction = recuperationCodeSousFonction(message);
			this.contenu = recuperationContenu(message);
		}
		return succes;
	}


/*** Récupération et vérification des données reçu ***/
	
	private short recuperationTailleDonnees(String message) {
		int debut = StructureTrame.TAILLE_ENTETE.getValue();
		int nbOctetsARecuperer = StructureTrame.TAILLE_TAILLE_DONNEES.getValue();
		if(message.length() < debut+nbOctetsARecuperer)
			return -1;
		String extrait = parcoursString(message, debut, nbOctetsARecuperer);
		return (short)(Integer.parseInt(extrait));
	}


	private short recuperationChecksum(String message) {
		int debut = message.length()-(StructureTrame.TAILLE_ENQUEUX.getValue()+StructureTrame.TAILLE_CHECKSUM.getValue());
		int nbOctetsARecuperer = StructureTrame.TAILLE_CHECKSUM.getValue();
		if(message.length() < debut+nbOctetsARecuperer)
			return -1;
		String extrait =  parcoursString(message, debut, nbOctetsARecuperer);
		return (short)(Integer.parseInt(extrait));
	}

	private short recuperationCodeFonction(String message) {
		int debut = StructureTrame.TAILLE_ENTETE.getValue()+StructureTrame.TAILLE_TAILLE_DONNEES.getValue();
		int nbOctetsARecuperer = StructureTrame.TAILLE_CODE_FONCTION.getValue();
		if(message.length() < debut+nbOctetsARecuperer)
			return -1;
		String extrait = parcoursString(message, debut, nbOctetsARecuperer);
		return (short)(Integer.parseInt(extrait));
	}
	
	private short recuperationCodeSousFonction(String message) {
		int debut = StructureTrame.TAILLE_ENTETE.getValue()+StructureTrame.TAILLE_TAILLE_DONNEES.getValue()+StructureTrame.TAILLE_CODE_FONCTION.getValue();
		int nbOctetsARecuperer = StructureTrame.TAILLE_CODE_SOUS_FONCTION.getValue();
		if(message.length() < debut+nbOctetsARecuperer)
			return -1;
		String extrait = parcoursString(message, debut, nbOctetsARecuperer);
		return (short)(Integer.parseInt(extrait));
	}



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
	
	private int testChecksum(String message) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	private String messagePourAgent(){
		String message = "";
		if(this.code_fonction == (short)(ConstructionCode.INITIALISATION.getValue())){
		}
		
		return message;
		
	}
	
	private String creationMessagePourAgent(){
		String message = "";
		
		return message;
	}

}
