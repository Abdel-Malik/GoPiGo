package transfertTrames;

public enum StructureTrame {

	TAILLE_ENTETE(2),
	ENTETE(0x01FE),
	TAILLE_TAILLE_DONNEES(2),
	TAILLE_CODE_FONCTION(1),
	TAILLE_CODE_SOUS_FONCTION(1),
	TAILLE_CHECKSUM(2),
	TAILLE_ENQUEUX(2),
	ENQUEUE(0x02FE);
	
	private short valeur;
	
	StructureTrame(int elm){
		this.valeur = (short) elm;
	}
	
	public short getValue(){
		return this.valeur;
	}
}
