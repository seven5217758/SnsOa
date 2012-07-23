package com.SnsOa.entity.portle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.portle.Portlets;
@Repository("PortletsDao")
public interface PortletsDao {
	public boolean save(Portlets comObj);
	public boolean update(Portlets comObj);
	public boolean delete(long id);
	public List<Portlets> query(String sqlstr);
	public Portlets getPortlets(long id);
	public List<Portlets> queryPage(String sqlstr,int nowpage,int maxRow);
}
