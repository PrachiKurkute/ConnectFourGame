package edu.nyu.pqs.connectfour;

/**
 *All Observers of the ConnectFourGame need to implement this interface
 *This interface has methods which gets called when state of the Game Changes
 */
public interface Observer {
	/**
	 * Indicates the Observers about start of the new game
	 * @param connectFour object of class ConnectFour
	 */
	public void gameStarted(ConnectFourGame connectFour);
	
	/**
	 * Informs the Observers that player has successfully inserted a coin
	 * @param connectFour object of class ConnectFour
	 */
	public void coinInserted(ConnectFourGame connectFour);
	
	/**
	 * Informs the Observers about winner player
	 * @param connectFour object of class ConnectFour
	 */
	public void win(ConnectFourGame connectFour);
	
	/**
	 * Informs the Observers about invalid attempt by the player
	 * @param errorMessage Appropriate error message
	 */
	public void invalidAttempt(String errorMessage);
}
