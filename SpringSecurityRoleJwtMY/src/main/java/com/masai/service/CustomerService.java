package com.masai.service;

import java.util.List;

import com.masai.model.Customer;

public interface CustomerService {

	public Customer addNewCustomer(Customer customer);

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(Integer customerId);

	public Customer getCustomerByUsername(String username);
}
