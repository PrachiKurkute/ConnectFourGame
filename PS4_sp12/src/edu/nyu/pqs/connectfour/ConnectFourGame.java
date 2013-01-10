package edu.nyu.pqs.connectfour;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *This is an Class contains the Model of ConnectFour Game.
 *It Encapsulates all the data structure and Methods used to Store and Modify
 *the state of the Game.
 *This class implements Subject Interface So that The Observers can get 
 *Notified of the current State of the game.
 *
 *@author Prachi Kurkute
 */
public class ConnectFourGame implements Subject {
	//stores number of rows on the game board
	private final int row;
	//stores number of columns on the game board
	private final int column;
	//stores current player who has a chance to play 
	private Player chance;
	//stores winner of the game
	private Player winner;
	//stores error message that might have been generated due 
	//to attempt to perform invalid operation by the player
	private String errorMessage;
	//stores status of the game board
	private GridCellStatus[][] grid;
	//stores observers of this class
	private List<Observer> observers;
	private static ConnectFourGame singleInstance = null;
	
	/**
	 * This is an inner builder class which builds the object of class
	 * ConnectFourGame.
	 * It builds 6 rows and 7 columns by default if not specified 
	 * 
	 * @author Prachi Kurkute
	 *
	 */
	public static class Builder {
		//initialized to default value
		private int row = 6;
		//initialized to default value
		private int column = 7;
		
		public Builder() {}
		
		/**
		 * Builds rows
		 * @throws IllegalArgumentException if row value is less than 4
		 * @param val number of rows
		 * @return Builder object
		 */
		public Builder row(int val) {
			if(val < 4) {
				throw new IllegalArgumentException(
						"Number of rows cannot be less than 4");
			}
			row = val;
			return this;
		}
		
		/**
		 * Builds columns
		 * @throws IllegalArgumentException if column value is less than 4
		 * @param val number of columns
		 * @return Builder object
		 */
		public Builder column(int val) {
			if(val < 4) {
				throw new IllegalArgumentException(
						"Number of columns cannot be less than 4");
			}
			column = val;
			return this;
		}
		
		/**
		 * Returns single instance of the built object
		 * @return ConnectFour object
		 */
		public ConnectFourGame getSingleInstance() {
			if (singleInstance == null) {
	            singleInstance = new ConnectFourGame(this);
	        }
			return singleInstance;
		}
	}
	
	/**
	 * Constructs the Object for the ConnectFourGame from getSingleInstance
	 * method
	 */
	private ConnectFourGame(Builder builder) {
		row = builder.row;
		column = builder.column;
		grid = new GridCellStatus[row][column];
		observers = new ArrayList<Observer>();
		chance = Player.playerOne;
	}
	
	/**
	 * Adds an Observer to the set of Observers for the ConnectFourGame Class.
	 * @throws NullPointerException If the Observer is Null
	 * @throws IllegalArgumentException If the Observer is already present 
	 * @param Observer
	 */
	@Override
	public void registerObserver(Observer o) {
		if(o == null) {
			throw new NullPointerException("Null pointer for Observer");
		}
		else {
			if(!observers.contains(0))
				observers.add(o);
			else
				throw new IllegalArgumentException("Observer Already Present");
		}
	}

	/**
	 * Removes an Observer from the set of Observers for the ConnectFourGame
	 * Class.
	 * @throws NullPointerException If the Observer is Null
	 * @throws IllegalArgumentException If the Observer is not present 
	 * in the set 
	 * @param Observer
	 */
	@Override
	public void removeObserver(Observer o) {
		int i;
		if(o == null) {
			throw new NullPointerException("Null pointer for Observer");
		}
		else {
			i = observers.indexOf(o);
			if (i >= 0) {
				observers.remove(i);
			}
			else {
				throw new IllegalArgumentException("Observer not Found");	
			}
		}
	}
	
	/**
	 * Starts a game
	 */
	public void startGame() {
		reset();
	}
	
	/**
	 * Resets status of the game
	 */
	public void reset() {
		int i;
		int j;
		chance = Player.playerOne; //First chance to player one
		winner = null;
		
		//Clear Grid
		for(i = 0; i < row; i++) {
			for(j = 0; j < column; j++) {
				grid[i][j] = GridCellStatus.empty;
			}
		}
		fireGameStartedEvent();
	}
	
	/**
	 * Inserts a coin in the game board at correct position in specified column
	 * If game board is full with coins throws
	 * If specified column is full with coins throws 
	 * @param columnIndex
	 */
	public void insertCoin(int columnIndex) {
		int i;
		boolean columnFull;
		boolean allowInsert; 
		boolean isWinning;
		
		if(isGridFull()) {
			errorMessage = "Grid is full. Cannot insert more coins";
			fireInvalidAttemptEvent(errorMessage);
			return;
		}
		
		columnFull = true;
		for(i = row-1; i >= 0; i--) {
			if(grid[i][columnIndex] == GridCellStatus.empty) {
				columnFull = false;
				break;
			}
		}
		
		if(columnFull == true) { //cannot insert coin in this column
			errorMessage = "This Column is full. Cannot insert coin";
			fireInvalidAttemptEvent(errorMessage);
			return;
		}
		else {
			allowInsert = checkAllowInsert(i, columnIndex);
			
			if(allowInsert == false) { //Cannot Insert Coin
				errorMessage = "Cannot insert coin at this position";
				fireInvalidAttemptEvent(errorMessage);
				return;
			}
			else { //can insert a coin
				if(chance == Player.playerOne) {  //Player One's Chance
					grid[i][columnIndex] = GridCellStatus.red;
					//fireCoinInsertedEvent();
					isWinning = isWinner(grid);
					if(isWinning == true) {
						fireWinEvent();
						reset();
						return;
					}
					chance = Player.playerTwo;
				}
				else if(chance == Player.playerTwo) {  //Player Two's Chance
					grid[i][columnIndex] = GridCellStatus.black;
					//fireCoinInsertedEvent();
					isWinning = isWinner(grid);
					if(isWinning == true) {
						fireWinEvent();
						reset();
						return;
					}
					chance = Player.playerOne;
				}
				fireCoinInsertedEvent();
			}
		}
	}
	
	/**
	 *Returns true if the coin can be inserted in given place on the game board 
	 *otherwise returns false
	 */
	public boolean checkAllowInsert(int rowIndex, int columnIndex) {
		if(grid[rowIndex][columnIndex] == GridCellStatus.empty) { //if place is empty
			if(rowIndex == (row - 1)) { //bottom-most non-empty place
				return true;
			}
			else { //rows other than bottom-most row
				if(grid[rowIndex+1][columnIndex] == GridCellStatus.empty) { //there is no coin below this place
					return false;
				}
				else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns true if game board is full of coins otherwise returns false
	 */
	public boolean isGridFull() {
		int i;
		int j;
		
		for(i = 0; i < row; i++) {
			for(j = 0; j < column; j++) {
				if(grid[i][j] == GridCellStatus.empty) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Returns true if insertion of coin has caused winning condition
	 * otherwise returns false
	 * @param GridCellStatus[][] multidimensional array representing grid
	 */
	public boolean isWinner(GridCellStatus[][] tempGrid) {
		int i;
		int j;
		int x;
		int y;
		int counter;
		GridCellStatus color = GridCellStatus.empty;
		
		if(chance == Player.playerOne) {
			color = GridCellStatus.red;
		}
		else if(chance == Player.playerTwo) {
			color = GridCellStatus.black;
		}
		counter = 4;
		
		//checking row-wise
		for(i = 0; i < row; i++) {
			counter = 4;
			for(j = 0; j <= (column - 4); j++) {
				counter = 4;
				x = j;
				while(x < column) {
					if(!(tempGrid[i][x] == color)) {
						counter = 4;
					}
					else if(tempGrid[i][x] == color) {
						counter--;
						if(counter == 0) {  //Winning Condition
							winner = chance;
							color = GridCellStatus.empty;
							return true;
						}
					}
					x++;
				}
			}
		}
		
		//checking column-wise
		for(i = 0; i < column; i++) {
			counter = 4;
			for(j = 0; j <= (row - 4); j++) {
				counter = 4;
				x = j;
				while(x < row) {
					if(!(tempGrid[x][i] == color)) {
						counter = 4;
					}
					else if(tempGrid[x][i] == color) {
						counter--;
						if(counter == 0) {  //Winning Condition
							winner = chance;
							color = GridCellStatus.empty;
							return true;
						}
					}
					x++;
				}
			}	
		}
		
		//checking left diagonal-wise
		for(i = 0; i <= (row - 4); i++) {
			for(j = 0; j <= (column - 4); j++) {
				counter = 4;
				x = i;
				y = j;
				while(x < row && y < column) {
					if(!(tempGrid[x][y] == color)) {
						counter = 4;
					}
					else if(tempGrid[x][y] == color) {
						counter--;
						if(counter == 0) {  //Winning Condition
							winner = chance;
							color = GridCellStatus.empty;
							return true;
						}
					}
					x++;
					y++;
				}
			}	
		}
		
		//checking right diagonal-wise
		for(i = 0; i <= (row - 4); i++) {
			for(j = 3; j < column; j++) {
				counter = 4;
				x = i;
				y = j;
				while(x < row && y >= 0) {
					if(!(tempGrid[x][y] == color)) {
						counter = 4;
					}
					else if(tempGrid[x][y] == color) {
						counter--;
						if(counter == 0) {  //Winning Condition
							winner = chance;
							color = GridCellStatus.empty;
							return true;
						}
					}
					x++;
					y--;
				}
			}	
		}
		
		return false;
	}
	
	/**
	 * Returns duplicate grid representing game board 
	 * @return GridCellStatus[][] array representing grid
	 */
	public GridCellStatus[][] getGrid() {
		GridCellStatus[][] tempGrid = new GridCellStatus[row][column];
		int i;
		int j;
		
		for(i = 0; i < row; i++) {
			for(j = 0; j < column; j++) {
				tempGrid[i][j] = grid[i][j];
			}
		}
		
		return tempGrid;
	}
	
	/**
	 * Returns the player who has now a chance to play 
	 * @return Player
	 */
	public Player getChance() {
		Player tempChance;
		
		tempChance = chance;
		
		return tempChance;
	}
	
	/**
	 * Returns the player who is winner
	 * @return Player
	 */
	public Player getWinner() {
		Player tempWinner;
		
		tempWinner = winner;
		
		return tempWinner;
	}
	
	/**
	 * Returns number of rows of the game board 
	 * @return int 
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Returns number of columns of the game board
	 * @return int
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Returns error message that might have been generated due to attempt 
	 * to perform invalid operation by the player
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	/**
	  * Notifies each Observer about the current state of the 
	  * ConnectFourGame when a new game starts
	  */
	public void fireGameStartedEvent() {
		for (Observer observer : observers) {
			observer.gameStarted(this);
		}
	}
	
	@Override
	/**
	  * Notifies each Observer about the current state of the 
	  * ConnectFourGame when a player successfully inserts a coin
	  */
	public void fireCoinInsertedEvent() {
		for (Observer observer : observers) {
			observer.coinInserted(this);
		}
	}
	
	@Override
	/**
	  * Notifies each Observer about the current state of the 
	  * ConnectFourGame when a player wins
	  */
	public void fireWinEvent() {
		for (Observer observer : observers) {
			observer.win(this);
		}
	}

	@Override
	/**
	  * Notifies each Observer about invalid attempt by the player
	  * @param String error message
	  */
	public void fireInvalidAttemptEvent(String errorMessage) {
		for (Observer observer : observers) {
			observer.invalidAttempt(errorMessage);
		}
	}
	
	/**
	 * Returns duplicate list containing registered observers
	 * This is used just for testing
	 * @return List<Observer> list of registered observers
	 */
	public List<Observer> getObservers() {
		List<Observer> tempObservers = new ArrayList<Observer>();
		Iterator itr;
		itr = observers.iterator();
		
		if(itr.hasNext()) {
			tempObservers.add((Observer)itr.next());
		}
		
		return tempObservers;
	}
	
}




























