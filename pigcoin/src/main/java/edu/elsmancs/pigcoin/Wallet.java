package edu.elsmancs.pigcoin;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import edu.elsmancs.pigcoin.GenSig;

public class Wallet {
	
	PublicKey adress = null;
	PrivateKey sKey = null;
	int total_input = 0;
	int total_output = 0;
	int balance = 0;
	int inputTransactions = 0;
	int outputTransactions = 0;

	public KeyPair generateKeyPair() {
		return GenSig.generateKeyPair();
	}
}

