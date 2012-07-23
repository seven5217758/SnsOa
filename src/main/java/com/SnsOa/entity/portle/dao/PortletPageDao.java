package com.SnsOa.entity.portle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.portle.PortletPage;

@Repository("PortletPageDao")
public interface PortletPageDao {
	public boolean save(PortletPage comObj);
	public boolean update(PortletPage comObj);
	public boolean delete(long id);
	public List<PortletPage> query(String sqlstr);
	public PortletPage getPortletPage(long id);
	public List<PortletPage> queryPage(String sqlstr,int nowpage,int maxRow);
}
