package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gameEngine.GameEngine;
import gameObjects.Ball;
import gui.Game.DrawCanvas;

public class GameGui implements ActionListener {
    JMenu menu;
    JMenuBar menubar;
    JMenuItem pauseButton, resumeButton, saveButton, loadButton, helpButton;

    //Game game = new Game(1350, 600);
    
    Game game;
    JFrame frame = new JFrame("Ball Launcher");
    
   
    
    private DrawCanvas canvas;
    private int canvasWidth, canvasHeight;
    
    double mouseX, mouseY;
    boolean mouseClickedBall;
    Level level_used;
    
 
    public Game getGame() {
    	return game;
    }

    
    public GameGui(Game game){
    	//game = GameEngine.getEngine().getGame();
    	this.game = game;
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(1350, 660);
    	frame.setLocationRelativeTo(null);
    	frame.setResizable(false);
    	menu = new JMenu("Menu");
        menubar = new JMenuBar();
        pauseButton = new JMenuItem("Pause");
        resumeButton = new JMenuItem("Resume");
        saveButton = new JMenuItem("Save");
        loadButton = new JMenuItem("Load");
        helpButton = new JMenuItem("Help");
        
        pauseButton.addActionListener(this);
        resumeButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        helpButton.addActionListener(this);
        
        menu.add(pauseButton);
        menu.add(resumeButton);
        menu.add(saveButton);
        menu.add(loadButton);
        menu.add(helpButton);
        
        menubar.add(menu);
        
        frame.setJMenuBar(menubar);
        frame.setLayout(null);
        frame.setContentPane(game);
        frame.setVisible(true);
        System.out.println("frame height: " + frame.getHeight());
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == pauseButton) {
			
		}
	}
}
