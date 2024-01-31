package com.cg.bsappl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bsappl.exception.*;
import com.cg.bsappl.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	//Customer findByCustId(int custId) throws CustomerException;

}
