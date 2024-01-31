package com.cg.bsappl.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.cg.bsappl.entities.Transaction;

public interface TransactionService {
	
	
	void addTransaction(Transaction transaction);

    List<Transaction> getTransactionsByAccountId(int accountId);
}
