package edu.nyu.pqs.connectfour;

import java.util.Random;

/**
 *This is a Class contains the Computer Player of ConnectFour Game.
 *It also acts as an Observer to ConnectFourGame
 *It creates only one instance of ComputerOpponent.
 *Computer Opponent always acts as a player-two.
 *Computer finds winning position for him if any.
 *Otherwise randomly inserts a coin in any column. 
 *@author Prachi Kurkute
 */
public class ComputerOpponent extends Peer implements Observer {
	private final ConnectFourGame connectFour;
	private Player chance;
	private int row;
	private int column;
	private static ComputerOpponent computerOpponent;
	
	/**
	 * Constructs the Object for the ComputerOpponent from getSingleInstance
	 * method
	 */
	private ComputerOpponent(final ConnectFourGame connectFour) {
		this.connectFour = connectFour;
		row = connectFour.getRow();
		column = connectFour.getColumn();
		
		connectFour.registerObserver(this);
	}
	
	/**
	 * Returns single instance of class ComputerOpponent
	 * implementing Singleton Design Pattern
	 */
	public static ComputerOpponent getSingleInstance(ConnectFourGame connectFour) {
		if (computerOpponent == null) {
			computerOpponent = new ComputerOpponent(connectFour);
        }
		return computerOpponent;
	}
	
	@Override
	/**
	 * Informs the Computer opponent that player has successfully inserted a 
	 * coin. Now Computer plays his turn. It finds winning position for him if 
	 * any. Otherwise randomly inserts a coin. 
	 * @param connectFour object of class ConnectFour
	 */
	public void coinInserted(ConnectFourGame connectFour) {
		Random random = new Random();
		int i;
		int j;
		int columnIndex;
		boolean gotPosition;
		boolean columnFull;
		boolean allowInsert;
		boolean isWinning;
		String errorMessage;
		GridCellStatus[][] grid;
		
		chance = connectFour.getChance();
		grid = connectFour.getGrid();
		columnIndex = random.nextInt(column);
		
		if(chance == Player.playerTwo) { // now Computer's chance
			//checking if grid is full
			if(connectFour.isGridFull()) {
				errorMessage = "Grid is full.Computer cannot insert more coin";
				connectFour.fireInvalidAttemptEvent(errorMessage);
				return;
			}
			
			//Finding any winning position
			isWinning = false;
			for(i = 0; i < column; i++) {
				columnFull = true;
				for(j = row-1; j >= 0; j--) {
					if(grid[j][i] == GridCellStatus.empty) {
						columnFull = false;
						break;
					}
				}
				if(columnFull == false) { //column not full
					allowInsert = connectFour.checkAllowInsert(j, i);
					
					if(allowInsert == true) { //Cannot Insert Coin
						grid[j][i] = GridCellStatus.black;
						isWinning = connectFour.isWinner(grid);
						grid[j][i] = GridCellStatus.empty;
						if(isWinning == true) {
							break;
						}
					}
				}
			}
			
			if(isWinning) {
				connectFour.insertCoin(i);
			}
			else { //no any winning position
				//Randomly finding column to insert a coin
				gotPosition = false;
				while(!gotPosition) {
					columnIndex = random.nextInt(column);
					
					columnFull = true;
					for(i = row-1; i >= 0; i--) {
						if(grid[i][columnIndex] == GridCellStatus.empty) {
							columnFull = false;
							break;
						}
					}
					if(columnFull == true) { //cannot insert coin in this column
						continue;
					}
					else {
						allowInsert = connectFour.checkAllowInsert(i, columnIndex);
						
						if(allowInsert == false) { //Cannot Insert Coin
							continue;
						}
						else {
							gotPosition = true;
						}
					}
				}
				if(gotPosition) {
					
					connectFour.insertCoin(columnIndex);
				}
			}
		}
		else if(chance == Player.playerOne){ // now player one's chance
			//do nothing
		}
	}

	@Override
	public void gameStarted(ConnectFourGame connectFour) {}

	@Override
	public void win(ConnectFourGame connectFour) {}

	@Override
	public void invalidAttempt(String errorMessage) {}
	
}
