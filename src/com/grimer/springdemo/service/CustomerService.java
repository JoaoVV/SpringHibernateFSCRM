package com.grimer.springdemo.service;

import java.util.List;

import com.grimer.springdemo.entity.Customer;

public interface CustomerService {
	
	List<Customer> getCustomers();

	void saveCustomer(Customer customer);

	Customer getCustomer(int id);

	void delete(int id);

}
