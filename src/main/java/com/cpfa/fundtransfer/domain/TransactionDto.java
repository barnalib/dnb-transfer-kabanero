package com.cpfa.fundtransfer.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDto {

	private int targetAccount;
	private String transactionType;
	private long accountBalance;
	private String transactionDesc;
    private long amount;
    private String transactionDatetime;
	private String comment;
	public TransactionDto(){

	}
	public TransactionDto(int targetAccount, String transactionType, long accountBalance, String transactionDesc,
			long amount, String comment) {
		super();
		this.targetAccount = targetAccount;
		this.transactionType = transactionType;
		this.accountBalance = accountBalance;
		this.transactionDesc = transactionDesc;
		this.amount = amount;
		this.comment = comment;
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.transactionDatetime=dateFormat.format(new Date());
	}
	public int getTargetAccount() {
		return targetAccount;
	}
	public void setTargetAccount(int targetAccount) {
		this.targetAccount = targetAccount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getTransactionDesc() {
		return transactionDesc;
	}
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getTransactionDatetime() throws ParseException {
		return transactionDatetime;
	}
	public void setTransactionDatetime(String transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "TransactionDto [targetAccount=" + targetAccount + ", "
				+ (transactionType != null ? "transactionType=" + transactionType + ", " : "") + "accountBalance="
				+ accountBalance + ", " + (transactionDesc != null ? "transactionDesc=" + transactionDesc + ", " : "")
				+ "amount=" + amount + ", "
				+ (transactionDatetime != null ? "transactionDatetime=" + transactionDatetime + ", " : "")
				+ (comment != null ? "comment=" + comment : "") + "]";
	}

}