package edu.nyu.pqs.connectfour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnectFourUI implements Observer , ActionListener{
	private final ConnectFourGame connectFour;
	private PeerFactory peer;
	private Peer player_one;
	private Peer player_two;
	private Player chance;
	private Player winner;
	private int row;
	private int column;
	List<JButton> dropButtons;
	List<JButton> gridElements;
	GridCellStatus[][] grid;
	Iterator itr;
	JFrame mainFrame;
	JFrame winMainFrame;
	JFrame startFrame;
	JFrame errorFrame;
	JLabel gameTitle;	
	JLabel winnerLabel;
	JLabel chanceLabel ;
	JLabel winnerDisplay;
	JLabel chanceDisplay;
	JLabel errorLabel;
	JLabel errorDisplay;
	JButton resetButton;
	JButton exitButton;
	JButton gridElement;
	JButton okButton;
	JButton errorOkButton;
	JButton onePlayer;
	JButton twoPlayer;
	JButton exit;
	JPanel gameGrid;
	JPanel gameButtons;
	JPanel gameMain;
	JPanel gameStatus;
	JPanel gameCommand;
	JPanel winPanel;
	JPanel startPanel;
	JPanel errorPanel;
	GridLayout gridLayout;
	
	/**
	 * Constructs the Object for the ConnectFourUI
	 */
	public ConnectFourUI(final ConnectFourGame connectFour) {
		peer = new PeerFactory();
		this.connectFour = connectFour;
		
		connectFour.registerObserver(this);
		startWindow();
	}
	
	/**
	 * Constructs the GUI for the starting window
	 */
	private void startWindow() {
		startFrame = new JFrame("Connect Four");
		
		startPanel = new JPanel();
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
		
		onePlayer = new JButton("One Player");
		onePlayer.addActionListener(this);
		twoPlayer = new JButton("Two Players");
		twoPlayer.addActionListener(this);
		exit = new JButton("EXIT");
		exit.addActionListener(this);
		
		startPanel.add(onePlayer);
		startPanel.add(twoPlayer);
		startPanel.add(exit);
		startFrame.getContentPane().add(BorderLayout.CENTER, startPanel);
		
		startFrame.setSize(200, 200);
		startFrame.pack();
		startFrame.setVisible(true);
	}
	
	/**
	 * Constructs the GUI for the game window
	 */
	private void loadGame() {
		int i;
		int j;
		chance = connectFour.getChance();
		winner = connectFour.getWinner();
		row = connectFour.getRow();
		column = connectFour.getColumn();
		dropButtons = new ArrayList<JButton> ();
		gridElements = new ArrayList<JButton> ();
		
		//main frame with default border layout
		mainFrame = new JFrame("Connect Four");
		
		//adding welcome message to north of border layout
		gameTitle = new JLabel("Welcome to Connect Four!!");
		mainFrame.getContentPane().add(BorderLayout.NORTH, gameTitle);
		
		//adding chance and winner information to east of border layout
		gameStatus = new JPanel();
		gameStatus.setBackground(Color.LIGHT_GRAY);
		gameStatus.setLayout(new BoxLayout(gameStatus, BoxLayout.Y_AXIS));
		
		chanceLabel = new JLabel("Now chance of: ");
		String str1 = "";
		if(chance == Player.playerOne) {
			str1 = Player.playerOne.name();
		}
		else if(chance == Player.playerTwo) {
			str1 = Player.playerTwo.name();
		}
		chanceDisplay = new JLabel(str1);

		gameStatus.add(chanceLabel);
		gameStatus.add(Box.createRigidArea(new Dimension(0, 5)));
		gameStatus.add(chanceDisplay);
		gameStatus.add(Box.createRigidArea(new Dimension(0, 5)));
		
		mainFrame.getContentPane().add(BorderLayout.EAST, gameStatus);
		
		//adding reset and exit buttons to south of border layout
		gameCommand = new JPanel();
		gameStatus.setBackground(Color.LIGHT_GRAY);
		
		resetButton = new JButton("RESET");
		resetButton.addActionListener(this);
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(this);
		
		gameCommand.add(resetButton);
		gameCommand.add(exitButton);
		
		mainFrame.getContentPane().add(BorderLayout.SOUTH, gameCommand);
		
		//adding panel to west of border layout
		//panel has box layout with flow layout buttonPanel and grid layout grid panel
		gameMain = new JPanel();
		gameMain.setLayout(new BoxLayout(gameMain, BoxLayout.Y_AXIS));
		
		gameButtons = new JPanel();
		
		for(i = 0; i < column; i++) {
			JButton dropButton = new JButton("Drop");
			dropButton.setName(i + "");
			dropButtons.add(dropButton);
		}
		
		itr = dropButtons.iterator();
		while(itr.hasNext()) {
			gameButtons.add((JButton)itr.next());
		}
		
		itr = dropButtons.iterator();
		while(itr.hasNext()) {
			((JButton) itr.next()).addActionListener(this);
		}
	
		gridLayout = new GridLayout(6, 7);
		gridLayout.setVgap(1);
		gridLayout.setHgap(2);
		gameGrid = new JPanel(gridLayout);
		
		for(i = 0; i < row; i++) {
			for(j = 0; j < column; j++) {
				gridElement = new JButton();
				gridElement.setBackground(Color.gray);
				gridElements.add(gridElement);
				gameGrid.add(gridElement);
			}
		}
		
		gameMain.add(gameButtons);
		gameMain.add(gameGrid);
		
		mainFrame.getContentPane().add(BorderLayout.WEST, gameMain);
		
		mainFrame.setSize(400, 400);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	@Override
	/**
	 * Informs the Observers that player has successfully inserted a coin
	 * @param connectFour object of class ConnectFour
	 */
	public void coinInserted(ConnectFourGame connectFour) {
		chance = connectFour.getChance();
		
		updateGrid(connectFour);
		updateChance(connectFour);
	}

	@Override
	/**
	 * Indicates the Observers about start of the new game
	 * @param connectFour object of class ConnectFour
	 */
	public void gameStarted(ConnectFourGame connectFour) {
		chance = connectFour.getChance();
		
		updateGrid(connectFour);
		updateChance(connectFour);
		mainFrame.setVisible(true);
	}

	@Override
	/**
	 * Informs the Observers about winner player
	 * @param connectFour object of class ConnectFour
	 */
	public void win(ConnectFourGame connectFour) {
		chance = connectFour.getChance();
		winner = connectFour.getWinner();
		
		updateGrid(connectFour);
		
		winMainFrame = new JFrame("Connect Four");
		winPanel = new JPanel();
		winPanel.setLayout(new BoxLayout(winPanel, BoxLayout.Y_AXIS));
		winnerLabel = new JLabel("Winner is: ");
		String str = "";
		if(winner == Player.playerOne) {
			str = Player.playerOne.name();
		}
		else if(winner == Player.playerTwo) {
			str = Player.playerTwo.name();
		}
		winnerDisplay = new JLabel(str);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		winPanel.add(winnerLabel);
		winPanel.add(winnerDisplay);
		winPanel.add(okButton);
		winMainFrame.getContentPane().add(BorderLayout.NORTH, winPanel);
		winMainFrame.setSize(200, 200);
		winMainFrame.setVisible(true);
	}

	/**
	 * Displays the current status of the grid
	 * @param connectFour
	 */
	private void updateGrid(ConnectFourGame connectFour) {
		int i;
		int j;
		grid = connectFour.getGrid();
		itr = gridElements.iterator();
		
		JButton temp;
		
		for(i = 0; i < row; i++) {
			for(j = 0; j< column; j++) {
				temp = (JButton)itr.next();
				if(grid[i][j] == GridCellStatus.empty) {
					temp.setBackground(Color.gray);
				}
				if(grid[i][j] == GridCellStatus.red) {
					temp.setBackground(Color.red);
				}
				if(grid[i][j] == GridCellStatus.black) {
					temp.setBackground(Color.black);
				}
			}
		}
	}
	
	/**
	 * Displays the current chance of the player
	 * @param connectFour
	 */
	private void updateChance(ConnectFourGame connectFour) {
		String str = "";
		if(chance == Player.playerOne) {
			str = Player.playerOne.name();
		}
		else if(chance == Player.playerTwo) {
			str = Player.playerTwo.name();
		}
		chanceDisplay.setText(str);
	}

	@Override
	/**
	 * Defines the actions to be performed by buttons used if it is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(exit) || 
				e.getSource().equals(exitButton)) {
			System.exit(0);
		}
		if(e.getSource().equals(onePlayer)) {
			startFrame.setVisible(false);
			player_one = peer.getPeer("human", connectFour);
			player_two = peer.getPeer("computer", connectFour);
			loadGame();
			connectFour.startGame();
		}
		if(e.getSource().equals(twoPlayer)) {
			startFrame.setVisible(false);
			player_one = peer.getPeer("human", connectFour);
			player_two = peer.getPeer("human", connectFour);
			loadGame();
			connectFour.startGame();
		}
		if(e.getSource().equals(resetButton)) {
			winMainFrame.setVisible(false);
			connectFour.reset();
		}
		if(e.getSource().equals(okButton)) {
			winMainFrame.setVisible(false);
		}
		if(e.getSource().equals(errorOkButton)) {
			errorFrame.setVisible(false);
		}
		JButton temp = (JButton) e.getSource();
		for(int i =0;i<dropButtons.size();i++){
			if(e.getSource().equals(dropButtons.get(i))){
				int columnIndex = Integer.parseInt(temp.getName());
				connectFour.insertCoin(columnIndex);
			}
		}
	}

	@Override
	/**
	 * Informs the Observers about invalid attempt by the player
	 * @param String error message
	 */
	public void invalidAttempt(String errorMessage) {
		errorFrame = new JFrame("Connect Four");
		errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		
		errorLabel = new JLabel("Error: ");
		errorDisplay = new JLabel(errorMessage);
		errorOkButton = new JButton("OK");
		errorOkButton.addActionListener(this);
		
		errorPanel.add(errorLabel);
		errorPanel.add(errorDisplay);
		errorPanel.add(errorOkButton);
		errorFrame.getContentPane().add(BorderLayout.NORTH, errorPanel);
		errorFrame.setSize(200, 200);
		errorFrame.setVisible(true);
	}
	
}









































































