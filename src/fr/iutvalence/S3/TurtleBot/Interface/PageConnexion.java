package fr.iutvalence.S3.TurtleBot.Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;

public class PageConnexion {

	private JFrame frame;
	private JTextField port;
	private JTextField ipAdress;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagePrincipale window = new PagePrincipale();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public PageConnexion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//titre
		JLabel titre = new JLabel("- Connexion au Robot -\r\n");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titre.setBounds(0, 9, 494, 39);
		frame.getContentPane().add(titre);
		JLabel imageTurtleBot = new JLabel();
		imageTurtleBot.setLocation(364, 102);
		frame.getContentPane().add(imageTurtleBot);
		imageTurtleBot.setSize(130,190);
		imageTurtleBot.setIcon(new ImageIcon(PageConnexion.class.getResource("/fr/iutvalence/S3/TurtleBot/Icones/Sans titre-3.png")));
		
		ipAdress = new JTextField();
		ipAdress.setHorizontalAlignment(SwingConstants.LEFT);
		ipAdress.setBounds(72, 102, 270, 20);
		frame.getContentPane().add(ipAdress);
		ipAdress.setColumns(10);
		
		port = new JTextField();
		port.setBounds(72, 170, 270, 20);
		frame.getContentPane().add(port);
		port.setColumns(10);
		
		JLabel lblAdresseIp = new JLabel("Adresse IP");
		lblAdresseIp.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresseIp.setBounds(72, 77, 270, 14);
		frame.getContentPane().add(lblAdresseIp);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setBounds(72, 145, 270, 14);
		frame.getContentPane().add(lblPort);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(162, 222, 89, 23);
		frame.getContentPane().add(btnConnexion);
	}
}
