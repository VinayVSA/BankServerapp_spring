package com.cg.bsappl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bsappl.entities.Account;
import com.cg.bsappl.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

	@Modifying
	@Query("SELECT t FROM Transaction t WHERE t.account.accountNum = :accountId")
	List<Transaction> findByAccountAccountId(int accountId);
}
