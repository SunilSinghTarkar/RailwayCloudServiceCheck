package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Admin;
import com.masai.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService service;
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/add")
	public ResponseEntity<Admin> insertAdminController(@RequestBody Admin admin) {

		admin.setRole("ROLE_ADMIN");
		admin.setPassword(encoder.encode(admin.getPassword()));
		Admin insertedAdmin = service.addNewAdmin(admin);
		return ResponseEntity.status(HttpStatus.CREATED).body(insertedAdmin);

	}

	@GetMapping("/get/{adminId}")
	public ResponseEntity<Admin> getAdminController(@PathVariable Integer adminId) {

		Admin admin = service.getAdminById(adminId);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);

	}

	@GetMapping("/get/username/{username}")
	public ResponseEntity<Admin> getAdminController(@PathVariable String username) {

		Admin admin = service.getAdminByUsername(username);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);

	}

	@GetMapping("/get")
	public ResponseEntity<List<Admin>> getAllAdmin() {

		List<Admin> admin = service.getAllAdmins();
		return new ResponseEntity<List<Admin>>(admin, HttpStatus.OK);

	}

}
