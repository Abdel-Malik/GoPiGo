package transfertTrames;

public enum ControleGopigo {

	CODE_FONCTION(0xA1),
	
	AVANCER (0x01),
	RECULER (0x02),
	GAUCHE (0x03),
	DROITE (0x04),
	STOP (0x05),
	ROTATION_GAUCHE (0x06),
	ROTATION_DROITE (0x07),
	ACCELERER (0x08),
	RALENTIR (0x09),
	ACTIONNER_SERVO_GAUCHE (0x0A),
	ACTINNER_SERVO_DROITE (0x0B),
	RE_AXEE_SERVO (0x0C);
	
	private short val;
	
	ControleGopigo(int elm) {
		this.val = (short)elm;
	}
	
	public short getValue(){
		return this.val;
	}
}
