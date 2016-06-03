package interfaceControle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JTextField;

import gopigo.InformationConnexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Interface de départ qui demande à l'utilisateur d'entrer l'adresse IP et le port auquels se connecter
 */
public class PageConnexion extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String ICON_PATH = "/icones/";
	
	private JTextField champIp;
	private JTextField champPort;
	private JButton btnConnexion;
	private boolean ok;

	
	
	public PageConnexion(JFrame frame, String client) 
	{
		super(frame, "Connexion", true);
		initialize(client);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == btnConnexion)
		{
			if(this.champIp.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "L'adresse IP n'est pas indiqué");
			}
			else if(this.champPort.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Le port n'est pas indiqué");
			else 
			{
				try
				{
					Integer.parseInt(this.champPort.getText());
				}
				catch(NumberFormatException et)
				{
					JOptionPane.showMessageDialog(this, "Le port doit être de type entier");
					return;
				}
				this.ok = true; 
				setVisible (false);
			}
			
		}
	}
	
	public InformationConnexion lanceDialogue()
	{ 
		this.ok = false ;
		setVisible (true) ;
		
		if (this.ok)
			return new InformationConnexion(this.champIp.getText(), Integer.parseInt(this.champPort.getText())) ;
		else 
			return null ;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String client)
	{
		this.setResizable(false);
		this.setBounds(100, 100, 500, 320);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		String nomFenetre = "- Connexion au Robot -\r\n";
		if(!client.equals("ROBOT"))
			nomFenetre = "- Connexion simulateur -\r\n";
		JLabel titre = new JLabel(nomFenetre);
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titre.setBounds(0, 9, 494, 39);
		this.getContentPane().add(titre);
		
		JLabel imageGopigo = new JLabel();
		imageGopigo.setLocation(364, 102);
		this.getContentPane().add(imageGopigo);
		imageGopigo.setSize(130,106);
		if(client.equals("ROBOT"))
			imageGopigo.setIcon(new ImageIcon(PageConnexion.class.getResource(ICON_PATH+"gopigo.png")));
		
		JPanel panel = new JPanel();
		panel.setBounds(72, 61, 270, 190);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAdresseIp = new JLabel("Adresse IP");
		lblAdresseIp.setBounds(0, 24, 270, 14);
		panel.add(lblAdresseIp);
		lblAdresseIp.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.champIp = new JTextField();
		this.champIp.setBounds(0, 41, 270, 20);
		panel.add(this.champIp);
		this.champIp.setHorizontalAlignment(SwingConstants.LEFT);
		this.champIp.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(0, 72, 270, 14);
		panel.add(lblPort);
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.champPort = new JTextField();
		this.champPort.setBounds(0, 87, 270, 20);
		panel.add(this.champPort);
		this.champPort.setColumns(10);
		
		this.btnConnexion = new JButton("Connexion");
		this.btnConnexion.setBounds(80, 135, 102, 23);
		panel.add(this.btnConnexion);
		this.btnConnexion.addActionListener(this);
		panel.setVisible(true);

	}

}
