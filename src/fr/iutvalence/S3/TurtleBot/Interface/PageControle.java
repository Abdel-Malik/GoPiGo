package fr.iutvalence.S3.TurtleBot.Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class PageControle {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageControle window = new PageControle();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PageControle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 603, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
