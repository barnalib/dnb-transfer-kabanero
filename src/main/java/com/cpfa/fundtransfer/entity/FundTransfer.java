package com.cpfa.fundtransfer.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FundTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int txId;
    private int accountNum;
    private String notification;
    private int txAccount;
    private String txName;
    private String description;
    private double amount;
    private String status;
    private Date transactionDatetime;


    public FundTransfer() {

    }


	public FundTransfer(int accountNum, int txAccount, String txName, String desc, double amount, String status,int txId) {
		super();
		this.accountNum = accountNum;
		this.txAccount = txAccount;
		this.txName = txName;
		this.description = desc;
		this.amount = amount;
		this.status = status;
		this.setTransactionDatetime(new Date());
		this.setTxId(txId);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTxAccount() {
		return txAccount;
	}


	public void setTxAccount(int txAccount) {
		this.txAccount = txAccount;
	}


	public String getTxName() {
		return txName;
	}


	public void setTxName(String txName) {
		this.txName = txName;
	}


	public String getDesc() {
		return description;
	}


	public void setDesc(String desc) {
		this.description = desc;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getTransactionDatetime() {
		return transactionDatetime;
	}


	public void setTransactionDatetime(Date transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}


	public int getAccountNum() {
		return accountNum;
	}


	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}


	public int getTxId() {
		return txId;
	}


	public void setTxId(int txId) {
		this.txId = txId;
	}


	public String getNotification() {
		return notification;
	}


	public void setNotification(String notification) {
		this.notification = notification;
	}

}
