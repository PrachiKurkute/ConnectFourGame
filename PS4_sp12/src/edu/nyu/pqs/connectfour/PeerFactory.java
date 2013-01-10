package edu.nyu.pqs.connectfour;

/**
 *This is a Class contains the method to return Player of ConnectFour Game.
 *Based on the given argument, the player can be Human or Computer Opponent
 *It creates only two instance of class Peer
 *@author Prachi Kurkute
 */
public class PeerFactory {
	private static int count = 1;
	
	public PeerFactory() {}
	
	/**
	 * Constructs the Object of class Peer
	 * Based on the given argument type, it returns instance of class Human
	 * or that of class Computer Opponent
	 * It creates only two instance of class Peer
	 */
	public Peer getPeer(String type, ConnectFourGame connectFour) {
		if(count > 2) {
			throw new UnsupportedOperationException(
					"Does not allow more than 2 instances"); 
		}
		else {
			count++;
			if(type.equals("computer")) {
				return ComputerOpponent.getSingleInstance(connectFour);
			}
			else {
				return new Human(connectFour);
			}
		}
	}
}
