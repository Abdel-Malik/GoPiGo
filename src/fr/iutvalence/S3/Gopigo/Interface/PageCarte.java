package fr.iutvalence.S3.Gopigo.Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;



/**
 * Classe créer pour l'affichage de la carte
 * Non appelée pour l'instant
 */
public class PageCarte 
{

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public PageCarte() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 287, 450);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("- Carte -");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.frame.getContentPane().add(label, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("Carte");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBackground(Color.BLACK);
		this.frame.getContentPane().add(label_1, BorderLayout.CENTER);
		
		this.frame.setVisible(true);
	}
}
