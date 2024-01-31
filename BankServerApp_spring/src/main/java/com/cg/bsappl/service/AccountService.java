package com.cg.bsappl.service;


//import java.sql.SQLException;

import com.cg.bsappl.entities.Account;
import com.cg.bsappl.exception.AccountException;
//import com.cg.bsappl.exception.AccountException;

public interface AccountService {

	 Account getAccountById(int accountNum);
        void deposit(int accountNum,double amount) throws AccountException;
        void withdraw(int accountNum,double amount)throws AccountException;
        void transfer(int fromaccountNum,int toaccountNum,double amount)throws AccountException;
	    void createAccount(Account account);

	    void deleteAccount(int accountNum);

	    void updateAccount(Account updatedAccount);

	    int getNumberOfAccounts();
	
}
