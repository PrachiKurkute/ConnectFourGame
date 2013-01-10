package edu.nyu.pqs.connectfour;
/**
 *This is a Subject Interface.
 *Objects use this interface to register as an observer 
 *and also to remove themselves from being observer
 *Our ConnectFourGame Model  will implement this Subject Interface
 */
public interface Subject {
	/**
	 * Adds an Observer to the set of Observers for the ConnectFourGame Class
	 * @throws NullPointerException If the Observer is Null
	 * @throws IllegalArgumentException If the Observer is already present 
	 * @param o Observer
	 */
	public void registerObserver(Observer o);
	
	/**
	  * Removes an Observer from the set of Observers for the ConnectFourGame
	  * Class
	  * @throws NullPointerException If the Observer is Null
 	  * @throws IllegalArgumentException If the Observer is not present 
	  * in the set 
	  * @param o Observer
	  */
	public void removeObserver(Observer o);
	
	/**
	  * Notifies each Observer about the current state of the 
	  * ConnectFourGame when a new game starts
	  */
	public void fireGameStartedEvent();
	
	/**
	  * Notifies each Observer about the current state of the 
	  * ConnectFourGame when a player successfully inserts a coin
	  */
	public void fireCoinInsertedEvent();
	
	/**
	  * Notifies each Observer about the current state of the 
	  * ConnectFourGame when a player wins
	  */
	public void fireWinEvent();
	
	/**
	  * Notifies each Observer about invalid attempt by the player
	  * @param errorMessage Appropriate error message
	  */
	public void fireInvalidAttemptEvent(String errorMessage);
}
