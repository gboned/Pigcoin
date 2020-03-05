package edu.elsmancs.pigcoin;

import edu.elsmancs.pigcoin.Wallet;
import edu.elsmancs.pigcoin.Transaction;

import static org.junit.Assert.*;

import org.junit.Test;

public class TransactionTest {

	@Test
	public void ConstructorTest() {
		Transaction transaction = new Transaction();
		Wallet wallet = new Wallet();
		wallet.generateKeyPair();
		assertNotNull(transaction);
	
	}
	
	@Test
	public void testGetters() {
		Wallet wallet_1 = new Wallet();
		Wallet wallet_2 = new Wallet();
		Transaction trx = new Transaction();
		trx = new Transaction("hash_1", "0", wallet_1.getAddress(), wallet_2.getAddress(), 20, "a flying pig!");
		
		assertEquals(trx.getpKey_sender(), wallet_1.getAddress());
		assertEquals(trx.getpKey_recipient(), wallet_2.getAddress());

	}

	
}
