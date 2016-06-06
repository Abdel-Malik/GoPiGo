package transfertTrames;

public enum StructureTrame {

	TAILLE_ENTETE(2),
	ENTETE(0x01FE),
	TAILLE_TAILLE_DONNEES(2),
	TAILLE_CODE_FONCTION(1),
	TAILLE_CODE_SOUS_FONCTION(1),
	TAILLE_CHECKSUM(2),
	TAILLE_ENQUEUX(2),
	ENQUEUE(0x02FE),
	NON_STRUCTUREE(0xFF),
	SEPARATEUR_ELEMENT(":"),
	SEPARATEUR_ENS_DONNEES(";");
	
	private short valeur;
	private String separation;
	
	StructureTrame(int elm){
		this.valeur = (short) elm;
	}
	
	StructureTrame(String caractere){
		this.separation = caractere;
	}
	
	public short getValue(){
		return this.valeur;
	}
	
	public String toString(){
		return this.separation;
	}
}
