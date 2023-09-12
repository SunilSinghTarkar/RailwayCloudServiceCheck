package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Admin;
import com.masai.repository.AdminRepository;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
	private AdminRepository adminRepo;

	@Override
	public Admin addNewAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepo.save(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return adminRepo.findAll();
	}

	@Override
	public Admin getAdminById(Integer adminId) {
		Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new RuntimeException("Admin not found!"));
		return admin;
	}

	@Override
	public Admin getAdminByUsername(String username) {
		Admin admin = adminRepo.findByUserName(username).orElseThrow(() -> new RuntimeException("Admin not found!"));
		return admin;
	}

}
