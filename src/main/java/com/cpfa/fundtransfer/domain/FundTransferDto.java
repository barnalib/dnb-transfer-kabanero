package com.cpfa.fundtransfer.domain;

public class FundTransferDto {

		private int accountNumber;
	    private int payeeAccount;
	    private double amount;
		private String payeeName;
	    private String transactionDesc;
	    private String requestStatus;
	    public FundTransferDto(int accountNumber, int payeeAccount, double amount, String payeeName,
				String transactionDesc) {
			super();
			this.accountNumber = accountNumber;
			this.payeeAccount = payeeAccount;
			this.amount = amount;
			this.payeeName = payeeName;
			this.transactionDesc = transactionDesc;
		}
		public FundTransferDto(){

	    }
		public int getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(int accountNumber) {
			this.accountNumber = accountNumber;
		}
		public int getPayeeAccount() {
			return payeeAccount;
		}
		public void setPayeeAccount(int payeeAccount) {
			this.payeeAccount = payeeAccount;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getPayeeName() {
			return payeeName;
		}
		public void setPayeeName(String payeeName) {
			this.payeeName = payeeName;
		}
		public String getTransactionDesc() {
			return transactionDesc;
		}
		public void setTransactionDesc(String transactionDesc) {
			this.transactionDesc = transactionDesc;
		}
		@Override
		public String toString() {
			return "FundTransferDto [accountNumber=" + accountNumber + ", payeeAccount=" + payeeAccount + ", amount="
					+ amount + ", " + (payeeName != null ? "payeeName=" + payeeName + ", " : "")
					+ (transactionDesc != null ? "transactionDesc=" + transactionDesc : "") + "]";
		}
		public String getRequestStatus() {
			return requestStatus;
		}
		public void setRequestStatus(String requestStatus) {
			this.requestStatus = requestStatus;
		}

}