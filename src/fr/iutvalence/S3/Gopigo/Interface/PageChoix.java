package fr.iutvalence.S3.Gopigo.Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
* Interface de départ qui demande à l'utilisateur d'entrer l'adresse IP et le port auquels se connecter
*/
public class PageChoix extends JFrame implements ActionListener, Runnable{

	private static final long serialVersionUID = 1L;
	JButton simu;
	JButton libre;
	String choix;
		
		
	public PageChoix() 
	{
		super("choix");
		this.choix = "";
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialize();
	}
	
	private void initialize()
	{
		this.setResizable(false);
		this.setBounds(100, 100, 400, 200);
		this.setLocationRelativeTo(null);
		
		JLabel titre = new JLabel("- type d'utilisation -\r\n");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("book antiqua", Font.PLAIN, 12));
		titre.setBounds(110, 20, 180, 25);
		this.getContentPane().add(titre);
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		this.simu = new JButton("robot en simulation");
		this.libre = new JButton("utilisation libre");
		this.simu.addActionListener(this);
		this.simu.setBounds(24, 80, 150, 25);
		this.libre.addActionListener(this);
		this.libre.setBounds(220, 80, 150, 25);
		panel.add(simu);
		panel.add(libre);
	}

	public void actionPerformed (ActionEvent ev)
	{
		if (ev.getSource() == this.simu)
		{
			this.choix = "simulation";
			this.setVisible(false);
		}
		else if(ev.getSource() == this.libre)
		{
			this.choix = "libre";
			this.setVisible(false);
		}
	}

	public String getChoix(){
			return this.choix;
	}
	@Override
	public void run() {
		this.setVisible(true);	
	}
}
