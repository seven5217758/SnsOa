package com.SnsOa.entity.portle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.portle.Portlet;
@Repository("PortletDao")
public interface PortletDao {
	public boolean save(Portlet comObj);
	public boolean update(Portlet comObj);
	public boolean delete(long id);
	public List<Portlet> query(String sqlstr);
	public Portlet getPortlet(long id);
	public List<Portlet> queryPage(String sqlstr,int nowpage,int maxRow);
}
