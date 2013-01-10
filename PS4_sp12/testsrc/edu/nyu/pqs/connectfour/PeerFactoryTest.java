package edu.nyu.pqs.connectfour;

import static org.junit.Assert.*;

import org.junit.Test;

public class PeerFactoryTest {
	private ConnectFourGame connectFourGame1;
	PeerFactory peerFactory1;

	/**
	 * Tests if getPeer factory method creates ComputerOpponent instance
	 */
	@Test
	public void testGetPeer1() {
		Peer peer;
		connectFourGame1 = new ConnectFourGame.Builder().row(6).column(7).getSingleInstance();
		peerFactory1 = new PeerFactory();
		peer = peerFactory1.getPeer("computer", connectFourGame1);
		assertTrue(peer instanceof ComputerOpponent);
	}
	
	/**
	 * Tests if getPeer factory method creates Human instance
	 */
	@Test
	public void testGetPeer2() {
		Peer peer;
		connectFourGame1 = new ConnectFourGame.Builder().row(6).column(7).getSingleInstance();
		peerFactory1 = new PeerFactory();
		peer = peerFactory1.getPeer("human", connectFourGame1);
		assertTrue(peer instanceof Human);
	}
	
	/**
	 * Tests if getPeer factory method creates only two instances
	 */
	@Test(expected=UnsupportedOperationException.class)
	public void testGetPeer3() {
		Peer peer1;
		Peer peer2;
		Peer peer3;
		connectFourGame1 = new ConnectFourGame.Builder().row(6).column(7).getSingleInstance();
		peerFactory1 = new PeerFactory();
		peer1 = peerFactory1.getPeer("computer", connectFourGame1);
		peer2 = peerFactory1.getPeer("human", connectFourGame1);
		peer3 = peerFactory1.getPeer("human", connectFourGame1);
	}

}
