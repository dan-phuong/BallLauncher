package gui;

import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gameEngine.GameEngine;

/**
 * Class that makes a gui that prompts use to load a save account
 * @author Dan
 *
 */
public class LoadPrompt extends JPanel implements ActionListener {
	JFrame parent;
	JPanel right, left;
	JTextField enterName;
	JButton submit;
	GameEngine engine = GameEngine.getEngine();
	
	/**
	 * Constructor for this class
	 * @param parent
	 */
	public LoadPrompt(JFrame parent) {
		this.parent = parent;
		parent.setLocationRelativeTo(null);
		this.setSize(500,500);
		this.setLayout(new GridLayout(1,2));
		
		right = new JPanel(new FlowLayout());
		left = new JPanel(new FlowLayout());
		
		enterName = new JTextField(20);
		submit = new JButton("Enter");
		submit.addActionListener(this);
		
		right.add(enterName);
		left.add(submit);
		
		
		this.add(right);
		this.add(left);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		String playerName;
		if(event.getSource() == submit) {
			try {
				playerName = enterName.getText();
				playerName = playerName.strip();
				
				if(playerName.equals("")) {
					System.out.println("Please type a name");
				}
				else {
					if(engine.getPlayers().containsKey(playerName)) {
						engine.setCurrentPlayer(playerName);
						parent.dispose();
						engine.displayGame();
						
						
					}
					else {
						System.out.println("player name not recognized");
					}
					
				}
				
			} catch(Exception ea) {
				
			}
		}	
	}
}
