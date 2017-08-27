package com.grimer.customertracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grimer.customertracker.entity.Customer;

@Service
public interface CustomerService {
	
	List<Customer> getCustomers();

	void saveCustomer(Customer customer);

	Customer getCustomer(int id);

	void delete(int id);

}
