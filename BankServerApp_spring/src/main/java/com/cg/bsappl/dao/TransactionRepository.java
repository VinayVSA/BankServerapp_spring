package com.cg.bsappl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bsappl.entities.Account;
import com.cg.bsappl.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}
