package transfertTrames;

public enum ConstructionCode {

	/** Codes fonctions **/
	INITIALISATION(0x01),
	INFORMATION(0x02),
	ORDRE(0x03),
	ENVIRONNEMENT(0x04),
	
	/** Codes sous-fonctions **/
	//type de messages
	ENVOI_MASH(0x00),
	RETOUR_MASH(0x10),
	ENVOI_AGENT(0x20),
	CONFIRMATION_AGENT(0x30),
	RETOUR_AGENT(0x40),
	PONCTUEL_AGENT(0x50),
	
	//information sujet du message
	ID(0x01),
	POSITION(0x02),
	COMPORTEMENT(0x03),
	VOISINAGE(0x04),
	COULEUR_FEU(0x05),
	VITESSE(0x06),
	TYPE_AGENT(0x07);
	
	private int valeur;
	
	ConstructionCode(int elm){
		this.valeur = elm;
	}
	
	public int getValue(){
		return this.valeur;
	}
}
