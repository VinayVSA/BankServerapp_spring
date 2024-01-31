package com.cg.bsappl.service;


import java.sql.SQLException;

import com.cg.bsappl.entities.Customer;
import com.cg.bsappl.exception.CustomerException;



public interface CustomerService {
  
	
	    Customer getCustomerDetailsById(int customerId);

	    void addCustomer(Customer customer);

	    void deleteCustomer(int customerId);

	    void updateCustomer(Customer customer)throws CustomerException;
  
	    int getNumberOfAccounts();
	
}
