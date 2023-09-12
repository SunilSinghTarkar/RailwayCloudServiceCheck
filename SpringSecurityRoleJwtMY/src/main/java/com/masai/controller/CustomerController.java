package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.UserDto;
import com.masai.service.AdminService;
import com.masai.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService service;
	@Autowired
	private AdminService adminService;
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/add")
	public ResponseEntity<Customer> insertAdminController(@RequestBody Customer customer) {

		customer.setRole("ROLE_USER");
		customer.setPassword(encoder.encode(customer.getPassword()));
		Customer insertedcustomer = service.addNewCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(insertedcustomer);

	}

	@GetMapping("/get/{customerId}")
	public ResponseEntity<Customer> getCustomerController(@PathVariable Integer customerId) {

		Customer customer = service.getCustomerById(customerId);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);

	}

	@GetMapping("/get/username/{username}")
	public ResponseEntity<Customer> getCustomerController(@PathVariable String username) {

		Customer customer = service.getCustomerByUsername(username);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);

	}

	@GetMapping("/get")
	public ResponseEntity<List<Customer>> getAllCustomer() {

		List<Customer> customer = service.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);

	}

	@GetMapping("/hello")
	public ResponseEntity<String> sayHello() {

		return new ResponseEntity<>("Hello this is sunil here.", HttpStatus.OK);

	}

	@GetMapping("/signIn")
	public ResponseEntity<UserDto> getLoggedInCustomerDetailsHandler(Authentication auth) {

		System.out.println("Hello" + auth); // this Authentication object having Principle object details
		String role = auth.getAuthorities().toArray()[0].toString().substring(5);
		UserDto user = new UserDto();
		if (role.equals("USER")) {
			Customer customer = service.getCustomerByUsername(auth.getName());
			user.setName(customer.getName());
			user.setUserId(customer.getCustomerId());
		} else {
			Admin admin = adminService.getAdminByUsername(auth.getName());
			user.setName(admin.getName());
			user.setUserId(admin.getAdminId());
		}
		user.setRole(role);
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);

	}
}
