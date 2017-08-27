package com.grimer.customertracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.grimer.customertracker.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	private static final String GET_CUSTOMERS = "from Customer order by lastName";
	private static final String DELETE_CUSTOMER = "delete from Customer where id=:customerId";
	private static final String CUSTOMER_ID = "customerId";
	private static final String SEARCH_QUERY = "from Customer where lower(firstName) like :theName or lower(lastName) like :theName";
	private static final String GET_ALL_CUSTOMERS = "from Customer";
	
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery(GET_CUSTOMERS, Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer customer = currentSession.get(Customer.class,id);
		
		return customer;
	}

	@Override
	@Transactional
	public void delete(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery(DELETE_CUSTOMER);
		query.setParameter(CUSTOMER_ID, id);
		
		query.executeUpdate();
	}
	
	@Override
	public List<Customer> searchCustomers(String theSearchName) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		// only search by name if theSearchName is not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(SEARCH_QUERY, Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery(GET_ALL_CUSTOMERS, Customer.class);			
		}
		
		List<Customer> customers = theQuery.getResultList();
				
		return customers;
		
	}

}






