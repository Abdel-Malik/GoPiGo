package gopigo;

public enum Ordre_robot {
	
	VITESSE_PLUS ("i"),
	
	VITESSE_MOINS ("d"),
	

	TOURNER_SERVO_G ("-"),
	
	TOURNER_SERVO_D ("+"),
	
	POS_SERVO_AXE ("*"),
	
	
	ULTRASON ("u"),
	
	POSITIONNEMENT ("P"),
	
	LOCALISATION ("c"),
	
	RESTART ("R"),
	
	TENSION ("U"),
	
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
