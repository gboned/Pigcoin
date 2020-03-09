package edu.elsmancs.pigcoin;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import edu.elsmancs.pigcoin.GenSig;

public class Wallet {
	
	private PublicKey address = null;
	private PrivateKey sKey = null;
	private double total_input = 0;
	private double total_output = 0;
	private double balance = 0;
	private List<Transaction> inputTransactions = null;
	private List<Transaction> outputTransactions = null;

	public Wallet() {}
	
	public PublicKey getAddress() {
		return address;
	}

	public void setAddress(PublicKey address) {
		this.address = address;
	}

	public PrivateKey getsKey() {
		return sKey;
	}

	public void setsKey(PrivateKey sKey) {
		this.sKey = sKey;
	}

	public double getTotal_input() {
		return total_input;
	}

	public void setTotal_input(double total_input) {
		this.total_input = total_input;
	}

	public double getTotal_output() {
		return total_output;
	}

	public void setTotal_output(double total_output) {
		this.total_output = total_output;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transaction> getInputTransactions() {
		return inputTransactions;
	}

	public void setInputTransactions(List<Transaction> inputTransactions) {
		this.inputTransactions = inputTransactions;
	}

	public List<Transaction> getOutputTransactions() {
		return outputTransactions;
	}

	public void setOutputTransactions(List<Transaction> outputTransactions) {
		this.outputTransactions = outputTransactions;
	}
	
	public void setSK(PrivateKey sKey) {
		this.sKey = sKey;
	}
	
	public void generateKeyPair() {
		KeyPair pair = GenSig.generateKeyPair();
		this.setSK(pair.getPrivate());
		this.setAddress(pair.getPublic());
	}
	
	public void updateBalance() {
		this.balance = this.getTotal_input() - this.getTotal_output();
	}
	
	public void loadCoins(BlockChain bChain) {
		double[] pigcoins = {0d, 0d};
		pigcoins = bChain.loadWallet(getAddress());
		setTotal_input(pigcoins[0]);
		setTotal_output(pigcoins[1]);
		updateBalance();
	}
	
	public void loadInputTransactions(BlockChain bChain) {
		setInputTransactions(bChain.loadInputTransactions(getAddress()));
	}
	
	public void loadOutputTransactions(BlockChain bChain) {
		setOutputTransactions(bChain.loadOutputTransactions(getAddress()));
	}
}

