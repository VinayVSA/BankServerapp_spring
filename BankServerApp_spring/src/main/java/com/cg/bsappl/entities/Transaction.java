package com.cg.bsappl.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="trans_id")
    private int TransactionId;
	@Column(name="trans_amount")
    private double amount;
	@Column(name="trans_type")
    private String type;
	@Column(name="trans_date")
    private LocalDate transactionDate;
    
    @ManyToOne()
	@JoinColumn(name = "accountNum")
	private Account account;

	public int getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [TransactionId=" + TransactionId + ", amount=" + amount + ", type=" + type
				+ ", transactionDate=" + transactionDate + ", account=" + account + "]";
	}

	public Transaction() {
		super();
		
	}

	public Transaction(double amount, String type, LocalDate transactionDate, Account account) {
		super();
		this.amount = amount;
		this.type = type;
		this.transactionDate = transactionDate;
		this.account = account;
	}
    

}
