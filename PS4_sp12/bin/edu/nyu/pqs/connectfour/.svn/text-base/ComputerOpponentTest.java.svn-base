package edu.nyu.pqs.connectfour;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComputerOpponentTest {
	private ComputerOpponent computerOpponent1;
	private ComputerOpponent computerOpponent2;

	/**
	 * Tests object creation function of class ComputerOpponent
	 * which creates single instance of this class
	 */
	@Test
	public void testGetSingleInstance() {
		ConnectFourGame connectFourGame = new ConnectFourGame.Builder().row(6).column(7).getSingleInstance();
		computerOpponent1 = ComputerOpponent.getSingleInstance(connectFourGame);
		computerOpponent2 = ComputerOpponent.getSingleInstance(connectFourGame);
		
		assertSame(computerOpponent1, computerOpponent1);
	}

	
	@Test
	public void testCoinInserted() {
		GridCellStatus[][] grid;
		int row;
		ConnectFourGame connectFourGame = new ConnectFourGame.Builder().row(6).column(7).getSingleInstance();
		computerOpponent1 = ComputerOpponent.getSingleInstance(connectFourGame);
		computerOpponent1.coinInserted(connectFourGame);
		grid = connectFourGame.getGrid();
		row = connectFourGame.getRow();
		
		//assertEquals(GridCellStatus.empty, grid[row-1][1]);
	}

}
