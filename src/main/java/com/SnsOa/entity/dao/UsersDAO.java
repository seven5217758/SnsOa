package com.SnsOa.entity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.Users;
@Repository("UsersDAO")
public interface UsersDAO {
	public boolean save(Users comObj);
	public boolean update(Users comObj);
	public boolean delete(long id);
	public List<Users> query(String sqlstr);
	public Users getUsers(long id);
}
