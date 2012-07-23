package com.SnsOa.entity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SnsOa.entity.Company;
import com.SnsOa.entity.Roles;
import com.SnsOa.entity.Users;

@Service("CompanyService")
public interface CompanyService {
	public String registerCompany(Company company);
	public Users Login(String account,String password);
	public List<Users> getCompanyUsers(long companyId);
	public Roles getRolesById(long id);
	public List<Roles> getCompanyRoles(long companyId);
	public boolean addRoles(Roles roles);
	public boolean deleteRoles(long rolesId);
	public Users getUserInfo(long id);
	public boolean deleteUserInfo(long id);
	public boolean updateUserInfo(Users user);
	public boolean addUser(Users user);
}
