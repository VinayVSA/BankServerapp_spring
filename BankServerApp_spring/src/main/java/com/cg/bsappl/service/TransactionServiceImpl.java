package com.cg.bsappl.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.bsappl.dao.TransactionRepository;
import com.cg.bsappl.entities.Transaction;
@Service
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        // Implementation to retrieve transactions by account ID
        return null;
    }

}
