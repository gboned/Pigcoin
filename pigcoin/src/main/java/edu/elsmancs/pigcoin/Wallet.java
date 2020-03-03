package edu.elsmancs.pigcoin;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

import edu.elsmancs.pigcoin.GenSig;

public class Wallet {
	
	private PublicKey adress = null;
	private PrivateKey sKey = null;
	private double total_input = 0;
	private double total_output = 0;
	private double balance = 0;
	private List<Transaction> inputTransactions = 0;
	private List<Transaction> outputTransactions = 0;


	public PublicKey getAdress() {
		return adress;
	}

	public void setAdress(PublicKey adress) {
		this.adress = adress;
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
}

