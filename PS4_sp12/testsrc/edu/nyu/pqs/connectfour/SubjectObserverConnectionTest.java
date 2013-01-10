package edu.nyu.pqs.connectfour;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;


public class SubjectObserverConnectionTest {
	ConnectFourGame connectFourGame1;
	ConnectFourGame connectFourGame2;
	ConnectFourUI connectFourUI1;
	ConnectFourUI connectFourUI2;
	ComputerOpponent computerOpponent1;
	Human human1;
	
	/**
	 * Tests if method registers an observer(ConnectFourUI) successfully
	 */
	@Test
	public void testRegisterObserver1() {
		List<Observer> temp;
		connectFourGame1 = new ConnectFourGame.Builder().row(6).column(8).
													getSingleInstance();
		connectFourUI1 = new ConnectFourUI(connectFourGame1);
		temp = connectFourGame1.getObservers();
		
		assertEquals(1, temp.size());
	}
	
	/**
	 * Tests if method registers an observer(Computer Opponent) successfully
	 */
	@Test
	public void testRegisterObserver2() {
		List<Observer> temp;
		connectFourGame1 = new ConnectFourGame.Builder().row(6).column(8).
													getSingleInstance();
		computerOpponent1 = ComputerOpponent.getSingleInstance(connectFourGame1);
		temp = connectFourGame1.getObservers();
		
		assertEquals(1, temp.size());
	}

	/**
	 * Tests if method registers an observer(Human) successfully
	 */
	@Test
	public void testRegisterObserver3() {
		List<Observer> temp;
		connectFourGame1 = new ConnectFourGame.Builder().row(6).column(8).
													getSingleInstance();
		human1 = new Human(connectFourGame1);
		temp = connectFourGame1.getObservers();
		
		assertEquals(1, temp.size());
	}
}
