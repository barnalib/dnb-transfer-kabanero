package com.cpfa.fundtransfer.domain;

import java.util.List;

public class TransactionRequest {

	private int accountNumber;
	private List<TransactionDto> transactions;
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public List<TransactionDto> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionDto> transactions) {
		this.transactions = transactions;
	}


}