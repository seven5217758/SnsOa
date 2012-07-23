package com.SnsOa.entity.portle.cargo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.portle.cargo.Cargo;

@Repository("CargoDao")
public interface CargoDao {
	public boolean save(Cargo comObj);
	public boolean update(Cargo comObj);
	public boolean delete(long id);
	public List<Cargo> query(String sqlstr);
	public Cargo getCompany(long id);
	public List<Cargo> queryPage(String sqlstr,int nowpage,int maxRow);
}
