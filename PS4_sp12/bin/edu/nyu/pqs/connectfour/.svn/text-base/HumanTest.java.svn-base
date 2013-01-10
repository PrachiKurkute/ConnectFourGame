package edu.nyu.pqs.connectfour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import junit.framework.TestCase;

public class HumanTest extends TestCase {
	private Human human1;
	private Human human2;

	/**
	 * Tests default constructor of class Human
	 */
	@Test
	public void testHuman() {
		ConnectFourGame connectFourGame = new ConnectFourGame.Builder().row(6).column(7).getSingleInstance();
		human1 = new Human(connectFourGame);
		human2 = new Human(connectFourGame);
		
		assertNotSame(human1, human2);
	}

}
