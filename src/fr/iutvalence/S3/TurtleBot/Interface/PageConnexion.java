package fr.iutvalence.S3.TurtleBot.Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JTextField;

import fr.iutvalence.S3.TurtleBot.Application;
import fr.iutvalence.S3.TurtleBot.Communication_wifi;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PageConnexion implements ActionListener
{

	private JFrame frame;
	private JTextField port;
	private JTextField ipAdress;
	
	private JLabel titre;
	private JLabel lblAdresseIp;
	private Panel panel;
	private JLabel lblPort;
	private JButton btnConnexion;

	/**
	 * Create the application.
	 */
	public PageConnexion() 
	{
		initialize();
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		Application application;
		try 
		{
			int newPort = Integer.parseInt(port.getText());
			application = new Application(new Communication_wifi(ipAdress.getText(), newPort));
			application.etablirConnexion();
			this.frame.setVisible(false);
			PageControl control = new PageControl();
			
		} 
		catch (NumberFormatException | IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//titre
		titre = new JLabel("- Connexion au Robot -\r\n");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titre.setBounds(0, 9, 494, 39);
		frame.getContentPane().add(titre);
		JLabel imageTurtleBot = new JLabel();
		imageTurtleBot.setLocation(364, 102);
		frame.getContentPane().add(imageTurtleBot);
		imageTurtleBot.setSize(130,190);
		imageTurtleBot.setIcon(new ImageIcon(PageConnexion.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/Sans titre-3.png")));
		
		panel = new Panel();
		panel.setBounds(72, 61, 270, 190);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblAdresseIp = new JLabel("Adresse IP");
		lblAdresseIp.setBounds(0, 24, 270, 14);
		panel.add(lblAdresseIp);
		lblAdresseIp.setHorizontalAlignment(SwingConstants.CENTER);
		
		ipAdress = new JTextField();
		ipAdress.setBounds(0, 41, 270, 20);
		panel.add(ipAdress);
		ipAdress.setHorizontalAlignment(SwingConstants.LEFT);
		ipAdress.setColumns(10);
		
		lblPort = new JLabel("Port");
		lblPort.setBounds(0, 72, 270, 14);
		panel.add(lblPort);
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		
		port = new JTextField();
		port.setBounds(0, 87, 270, 20);
		panel.add(port);
		port.setColumns(10);
		
		btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(80, 135, 102, 23);
		panel.add(btnConnexion);
		btnConnexion.addActionListener(this);
		panel.setVisible(true);
		frame.setVisible(true);
	}

}
