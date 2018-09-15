package models;

import java.util.ArrayList;

public class Account {
	private ArrayList<Share> shares;
	private ArrayList<Transaction> transactions;
	private int type;
	private long accountNumber;
	private ArrayList<Integer> sectors;
	private int balance;
	private String name;
	private String email;
	
	//TODO: doublecheck those params.
	
	Account(){
		
	}

}

// TODO:
// Build enum for sector
//TODO:
//Build enum for type
