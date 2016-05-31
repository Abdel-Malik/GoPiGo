package gopigo;

public enum Ordre_robot {
	
	ID ("i"),
	DEMANDE_ID ("I"),
	
	POSITION ("p"),
	
	DEMANDE_POSITION ("P"),
	
	COMPORTEMENT ("c"),
	
	DEMANDE_COMPORTEMENT ("C"),
	
	DEMANDE_VOISINAGE ("V"),
	
	VITESSE_PLUS ("+"),
	
	VITESSE_MOINS ("-"),
	
	DEMANDE_VITESSE ("D"),
	

	TOURNER_SERVO_G ("o"),
	
	TOURNER_SERVO_D ("e"),
	
	POS_SERVO_AXE ("*"),
	
	DISTANCE_OBSTACLE("u"),
	
	
	REINITIALISATION_POSITION ("r"),
	
	DEMANDE_TENSION ("U"),
	
	BREAK ("BREAK");
	
	private String ordre="";
	
	private Ordre_robot(String ordreRobot) 
	{
		this.ordre=ordreRobot;
	}
	
	@Override
	public String toString() 
	{
		return this.ordre;
	}
}
