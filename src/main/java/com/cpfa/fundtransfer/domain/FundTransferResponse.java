package com.cpfa.fundtransfer.domain;

import java.util.List;

import com.cpfa.fundtransfer.entity.FundTransfer;

public class FundTransferResponse {

	private int accountNumber;
	private List<FundTransfer> transfers;
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public List<FundTransfer> getTransfers() {
		return transfers;
	}
	public void setTransfers(List<FundTransfer> transfers) {
		this.transfers = transfers;
	}


}