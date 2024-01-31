package com.cg.bsappl.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bsappl.exception.*;
import com.cg.bsappl.dao.CustomerRepository;
import com.cg.bsappl.entities.Customer;
@Service
public class CustomerServiceImpl implements CustomerService {

	
	
	private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerDetailsById(int customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void updateCustomer(Customer customer)throws CustomerException {
    	Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCustomerId());
		if (optionalCustomer.isPresent()) {
			// Implement your validation logic if needed
			Customer existingCustomer = optionalCustomer.get();
			existingCustomer.setFirstName(customer.getFirstName());;
			existingCustomer.setLastName(customer.getLastName());
			existingCustomer.setEmailId(customer.getEmailId());;

			customerRepository.save(existingCustomer);
		} else {
			throw new CustomerException("Customer not found with ID: " + customer.getCustomerId());
		}

	}

	@Override
	public int getNumberOfAccounts() {
		// TODO Auto-generated method stub
		return (int) customerRepository.count();
	}
    }
	

