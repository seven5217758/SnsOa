package com.SnsOa.entity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.Roles;
@Repository("RolesDao")
public interface RolesDao {
	public boolean save(Roles roles);
	public boolean update(Roles roles);
	public boolean delete(long id);
	public List<Roles> query(String sqlstr);
	public Roles getRoles(long id);
}
