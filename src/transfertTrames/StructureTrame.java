package transfertTrames;

public enum StructureTrame {

	TAILLE_ENTETE(2),
	ENTETE(0x01FE),
	TAILLE_TAILLE_DONNEES(2),
	TAILLE_CODE_FONCTION(2),
	TAILLE_CODE_SOUS_FONCTION(2),
	TAILLE_CHECKSUM(2),
	TAILLE_ENQUEUX(2),
	ENQUEUE(0x02FE);
	
	private int valeur;
	
	StructureTrame(int elm){
		this.valeur = elm;
	}
	
	public int getValue(){
		return this.valeur;
	}
}
