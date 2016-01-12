package fr.iutvalence.S3.TurtleBot;

public class Vitesse {
	
	private double vitesseActuelle;
	
	public Vitesse(){
		
	}
	
	public void incrementerVitesse(){
		this.vitesseActuelle++;
	}
	
	public void decrementerVitesse(){
		this.vitesseActuelle--;
	}
	
	public void modifierVitesse(double vitesse){
		this.vitesseActuelle = vitesse;
	}
	
	public double getVitesse(){
		return this.vitesseActuelle;
	}
	
}
