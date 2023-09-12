package com.masai.service;

import java.util.List;

import com.masai.model.Admin;

public interface AdminService {
	public Admin addNewAdmin(Admin admin);

	public List<Admin> getAllAdmins();

	public Admin getAdminById(Integer adminId);

	public Admin getAdminByUsername(String username);
}
