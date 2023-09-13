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

		return new ResponseEntity<>("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Welcome Page</title>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            background-color: #f0f0f0;\r\n"
				+ "            margin: 0;\r\n"
				+ "            padding: 0;\r\n"
				+ "            display: flex;\r\n"
				+ "            flex-direction: column;\r\n"
				+ "            align-items: center;\r\n"
				+ "            justify-content: center;\r\n"
				+ "            height: 100vh;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        .container {\r\n"
				+ "            text-align: center;\r\n"
				+ "            background-color: #fff;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            padding: 40px;\r\n"
				+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        h1 {\r\n"
				+ "            font-size: 36px;\r\n"
				+ "            color: #333;\r\n"
				+ "            margin-bottom: 20px;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        p {\r\n"
				+ "            font-size: 18px;\r\n"
				+ "            color: #777;\r\n"
				+ "            margin-bottom: 30px;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        .button-container {\r\n"
				+ "            display: flex;\r\n"
				+ "            justify-content: center;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        .btn {\r\n"
				+ "            background-color: #007bff;\r\n"
				+ "            color: #fff;\r\n"
				+ "            padding: 12px 24px;\r\n"
				+ "            border: none;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            font-size: 16px;\r\n"
				+ "            cursor: pointer;\r\n"
				+ "            transition: background-color 0.3s ease;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        .btn:hover {\r\n"
				+ "            background-color: #0056b3;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"container\">\r\n"
				+ "        <h1>Welcome to Our Website 🎉</h1>\r\n"
				+ "        <p>Discover amazing content and services 😊.</p>\r\n"
				+ "        <div class=\"button-container\">\r\n"
				+ "           <h1 class=\"btn\"> Built by Sunil Singh Tarkar</h1>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n"
				+ "", HttpStatus.OK);

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
