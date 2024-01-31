package com.cg.bsappl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.cg.bsappl.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	
	@Modifying
    @Query("UPDATE Account a SET a.balance = a.balance - :amount WHERE a.accountNum = :accountId")
    void withdraw(int accountId, double amount);
}
