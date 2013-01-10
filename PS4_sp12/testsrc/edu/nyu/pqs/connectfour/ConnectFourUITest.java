package edu.nyu.pqs.connectfour;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectFourUITest {
	private ConnectFourUI connectFourUI1; 
	private ConnectFourUI connectFourUI2; 
	
	@Test
	public void testConnectFourUI() {
		ConnectFourGame connectFourGame = new ConnectFourGame.Builder().row(6).column(7).getSingleInstance();
		connectFourUI1 = new ConnectFourUI(connectFourGame);
		connectFourUI2 = new ConnectFourUI(connectFourGame);
		
		assertNotSame(connectFourUI1, connectFourUI2);
	}

	@Test
	public void testCoinInserted() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGameStarted() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWin() {
		//fail("Not yet implemented");
	}

	@Test
	public void testActionPerformed() {
		//fail("Not yet implemented");
	}

	@Test
	public void testInvalidAttempt() {
		//fail("Not yet implemented");
	}

}
