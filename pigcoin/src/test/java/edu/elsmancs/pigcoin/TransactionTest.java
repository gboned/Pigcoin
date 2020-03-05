package edu.elsmancs.pigcoin;

import edu.elsmancs.pigcoin.Wallet;
import edu.elsmancs.pigcoin.Transaction;

import static org.junit.Assert.*;

import org.junit.Test;

public class TransactionTest {
	
	private Transaction transaction;
	private Wallet wallet;

	@Test
	public void ConstructorTest() {
		transaction = new Transaction();
		wallet = new Wallet();
		wallet.generateKeyPair();
		assertNotNull(transaction);
		
	}

}
