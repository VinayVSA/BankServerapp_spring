package com.cg.bsappl.service;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bsappl.entities.Transaction;
import com.cg.bsappl.exception.AccountException;
//import com.cg.bsappl.exception.AccountException;
import com.cg.bsappl.dao.*;
import com.cg.bsappl.entities.Account;

import jakarta.transaction.Transactional;


@Service
public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository accountRepository;
	
	private final TransactionRepository transactionRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository=transactionRepository;
    }
    
    

    @Override
    public Account getAccountById(int accountNum) {
        return accountRepository.findById(accountNum).orElse(null);
    }

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(int accountNum) {
        accountRepository.deleteById(accountNum);
    }

    @Override
    public void updateAccount(Account updatedAccount) {
        accountRepository.save(updatedAccount);
    }

    @Override
    public int getNumberOfAccounts() {
        return (int) accountRepository.count();
    }

	@Transactional
	public void deposit(int accountNum, double amount)throws AccountException {
		Account acc=getAccountById(accountNum);
		if(acc==null)
		{
			throw new AccountException("the account with account number :"+accountNum+" doesnot exist");
		}
		else
		{
			double bal=acc.getBalance()+amount;
			acc.setBalance(bal);
			accountRepository.save(acc);
			Transaction trans=new Transaction();
			trans.setAmount(amount);
			trans.setType("deposit");
			trans.setTransactionDate(LocalDate.now());
			trans.setAccount(acc);
			transactionRepository.save(trans);
		}
		
		
		
	}

	@Transactional
	public void withdraw(int accountNum, double amount)throws AccountException {
		
		Account acc1=getAccountById(accountNum);
		if(acc1==null)
		{
			throw new AccountException("the account with account number :"+accountNum+" doesnot exist");
		}
		else if (acc1.getBalance() < amount)
		{
			throw new AccountException("Insufficient balance for withdrawal from account number: " + accountNum);
		}
		else
		{
			double bal=acc1.getBalance()-amount;
			acc1.setBalance(bal);
			accountRepository.save(acc1);
			
			Transaction trans1=new Transaction();
			trans1.setAmount(amount);
			trans1.setType("withdraw");
			trans1.setTransactionDate(LocalDate.now());
			trans1.setAccount(acc1);
			transactionRepository.save(trans1);
		}
		
	}

	@Transactional
	public void transfer(int fromaccountNum, int toaccountNum, double amount)throws AccountException {
		Account account1=getAccountById(fromaccountNum);
		Account account2=getAccountById(toaccountNum);
		if(account1.getBalance()>=amount)
		{
			System.out.println("The current balance of account number "+account1.getAccountNum()+" is:"+account1.getBalance());
			System.out.println("The current balance of account number "+account2.getAccountNum()+" is:"+account2.getBalance());
			
			account1.setBalance(account1.getBalance()-amount);
			accountRepository.save(account1);
			
			Transaction transferOutTransaction = new Transaction(amount,"transfer_out",LocalDate.now(),account1);
			transactionRepository.save(transferOutTransaction);
			
			System.out.println("Balance of account "+account1.getAccountNum() +"After transfer of amount="+amount+"from account number:"+account1.getAccountNum()+" is:"+account1.getBalance());
			
			
			account2.setBalance(account2.getBalance()+amount);
			accountRepository.save(account2);
			
			Transaction transferInTransaction = new Transaction(amount,"transfer_in",LocalDate.now(),account2);
			transactionRepository.save(transferInTransaction);
			
			System.out.println("Balance of account "+account2.getAccountNum() +"After transfer of amount="+amount+"to account number:"+account2.getAccountNum()+" is:"+account2.getBalance());
		}
		else
		{
			throw new AccountException("Not sufficient amount to tranfer from account number"+account1.getAccountNum()+"to account number"+account2.getAccountNum());
		}
		
	}
	   

}
