package models;

import java.util.ArrayList;

public class Account {
	private ArrayList<Share> shares;
	private ArrayList<Transaction> transactions;
	private int type;
	private Long accountNumber;
	private ArrayList<Integer> sectors;
	private int balance;
	private String name;
	private String email;
	
	//TODO: doublecheck those params.
	
	public Account(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getAccountNumber() {
		return this.accountNumber;
	}

	public void setName(String newName) {
		this.name = newName;
	}
}

// TODO:
// Build enum for sector
//TODO:
//Build enum for type
