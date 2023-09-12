package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Customer addNewCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {

		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found!"));
		return customer;
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		Customer customer = customerRepo.findByuserName(username)
				.orElseThrow(() -> new RuntimeException("Customer not found!"));
		return customer;
	}

}
