package com.veeriyaperumal.loanapplication.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class LoanData {

	private int loanNumber,customerId;
	private float loanAmount, loanPaidAmount, loanInterestPercentage, loanPendingAmount, loanIntresetAmount,
			loanTotalPayableAmount;
	private LocalDate loanIssueDate;

	public int getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(int loanNumber) {
		this.loanNumber = loanNumber;
	}

	public float getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(float loanAmount) {
		this.loanAmount = loanAmount;
	}

	public float getLoanPaidAmount() {
		return loanPaidAmount;
	}

	public void setLoanPaidAmount(float loanPaidAmount) {
		this.loanPaidAmount = loanPaidAmount;
	}

	public float getLoanPendingAmount() {
		return loanPendingAmount;
	}

	public void setLoanPendingAmount(float loanPendingAmount) {
		this.loanPendingAmount = loanPendingAmount;
	}

	public float getLoanIntresetAmount() {
		return loanIntresetAmount;
	}

	public void setLoanIntresetAmount(float loanIntresetAmount) {
		this.loanIntresetAmount = loanIntresetAmount;
	}

	public float getLoanInterestPercentage() {
		return loanInterestPercentage;
	}

	public void setLoanInterestPercentage(float loanInterestPercentage) {
		this.loanInterestPercentage = loanInterestPercentage;
	}

	public float getLoanTotalPayableAmount() {
		return loanTotalPayableAmount;
	}

	public void setLoanTotalPayableAmount(float loanTotalPayableAmount) {
		this.loanTotalPayableAmount = loanTotalPayableAmount;
	}

	public LocalDate getLoanIssueDate() {
		return loanIssueDate;
	}

	public void setLoanIssueDate(LocalDate loanIssueDate) {
		this.loanIssueDate = loanIssueDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
