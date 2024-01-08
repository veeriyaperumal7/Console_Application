package com.veeriyaperumal.loanapplication.model;

import java.time.LocalDate;

public class TransactionData {

	private int transactionId;
	private float creditAmount, debitAmount;
	private LocalDate paymentDate;

	public float getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(float creditAmount) {
		this.creditAmount = creditAmount;
	}

	public float getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(float debitAmount) {
		this.debitAmount = debitAmount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

}
