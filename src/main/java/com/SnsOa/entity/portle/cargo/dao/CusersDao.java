package com.SnsOa.entity.portle.cargo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.portle.cargo.Cusers;

@Repository("CusersDao")
public interface CusersDao {
	public boolean save(Cusers comObj);
	public boolean update(Cusers comObj);
	public boolean delete(long id);
	public List<Cusers> query(String sqlstr);
	public Cusers getCompany(long id);
	public List<Cusers> queryPage(String sqlstr,int nowpage,int maxRow);
}
