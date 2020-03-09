package edu.elsmancs.pigcoin;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public Map<String, Double> collectCoins(double pigcoins){
		
		Map<String, Double> collectedCoins = new LinkedHashMap<>();
		
		if (getInputTransactions() == null) {
			return null;
		}
		
		if (pigcoins > getBalance()) {
			return null;
		}
		
		Double achievedCoins = 0d;
		
		Set<String> consumedCoins = new HashSet<>();
		if (getOutputTransactions() != null) {
			for (Transaction transaction : getOutputTransactions()) {
				consumedCoins.add(transaction.getPrev_hash());
			}
		}
		
		for (Transaction transaction : getInputTransactions()) {
			
			if (consumedCoins.contains(transaction.getHash())) {
				continue;
			}
			
			if (transaction.getPigcoins() == pigcoins) {
				collectedCoins.put(transaction.getHash(), transaction.getPigcoins());
				consumedCoins.add(transaction.getHash());
				break;
			} else if (transaction.getPigcoins() > pigcoins) {
				collectedCoins.put(transaction.getHash(), pigcoins);
				collectedCoins.put("CA:" + transaction.getHash(), transaction.getPigcoins() - pigcoins);
				consumedCoins.add(transaction.getHash());
				break;
			} else {
				collectedCoins.put(transaction.getHash(), transaction.getPigcoins());
				achievedCoins = transaction.getPigcoins();
				pigcoins = pigcoins - achievedCoins;
				consumedCoins.add(transaction.getHash());
			}
		}
		
		return collectedCoins;
	}
	
	public byte[] signTransaction(String message) {
		return GenSig.sign(getsKey(), message);
	}
	
	public void sendCoins(PublicKey pKey_recipient, Double coins, String message, BlockChain bChain) {
		
		Map<String, Double> consumedCoins = new LinkedHashMap<>();
		
		consumedCoins = collectCoins(coins);
		
		if (consumedCoins != null) {
			bChain.processTransactions(getAddress(), pKey_recipient, consumedCoins, message, signTransaction(message));
		}
		
		this.loadCoins(bChain);
	}
}

