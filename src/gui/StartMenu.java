package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JFrame;

import gameEngine.GameEngine;

/**
 * Start menu for the DAP application
 * @author Dan
 *
 */
public class StartMenu extends JPanel implements ActionListener{
	JPanel top, middle, bottom;
	JButton newPlayer, loadPlayer;
	JFileChooser fileSelect;
	JTextArea startDisplay;
	static GameEngine engine = GameEngine.getEngine();
	static JFrame parent;
	
	/**
	 * Constructor for this class
	 * @param parent
	 */
	public StartMenu(JFrame parent) {
		this.parent = parent;
		this.setSize(500,500);
		this.setLayout(new GridLayout(3,0));
		
		top = new JPanel(new FlowLayout());
		middle = new JPanel(new FlowLayout());
		bottom = new JPanel(new FlowLayout());
		
		startDisplay = new JTextArea(2, 20);
		startDisplay.setText("Ball Launcher\nPlease select a player");
		startDisplay.setEditable(false);
		top.add(startDisplay);
		
		// Create New Player Button
		newPlayer = new JButton("New Player");
		
		// Adds actionListener
		newPlayer.addActionListener(this);
		loadPlayer = new JButton("Load Player");
		loadPlayer.addActionListener(this);
		middle.add(newPlayer);
		bottom.add(loadPlayer);
		
		this.add(top);
		this.add(middle);
		this.add(bottom);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == newPlayer) {
			System.out.println("new player");
			parent.dispose();
			
			engine.displayStart();
			
		}
		else if(e.getSource() == loadPlayer) {
			System.out.println("Load player");
			parent.dispose();
			engine.displayLoad();
			

		}
	}
	
}
