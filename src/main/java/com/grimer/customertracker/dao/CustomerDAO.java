package com.grimer.customertracker.dao;

import java.util.List;

import com.grimer.customertracker.entity.Customer;

public interface CustomerDAO {

	List<Customer> getCustomers();

	void saveCustomer(Customer customer);

	Customer getCustomer(int id);

	void delete(int id);

	List<Customer> searchCustomers(String theSearchName);
	
}
