package com.cg.bsappl.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@Column(name= "accountNum")
	private int accountNum;
	@Column(name= "balance")
	private double balance;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
    private Customer customer;
    
	public Account()
	{
		
	}
	public Account(int accountNum, double balance, Customer customer) {
		super();
		this.accountNum = accountNum;
		this.balance = balance;
		this.customer = customer;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", balance=" + balance + ", customer=" + customer + "]";
	}
	
	

}
