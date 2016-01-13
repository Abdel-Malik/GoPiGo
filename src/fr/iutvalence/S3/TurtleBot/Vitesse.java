package fr.iutvalence.S3.TurtleBot;

public class Vitesse {
	
	private double vitesseActuelle;
	
	public Vitesse(){
		this.vitesseActuelle = 0;
	}
	
	public void incrementerVitesse(){
		this.vitesseActuelle = vitesseActuelle + 0.2;
	}
	
	public void decrementerVitesse(){
		this.vitesseActuelle = vitesseActuelle - 0.2;
	}
	
	public void modifierVitesse(double vitesse){
		this.vitesseActuelle = vitesse;
	}
	
	public double getVitesse(){
		return this.vitesseActuelle;
	}
	
}
