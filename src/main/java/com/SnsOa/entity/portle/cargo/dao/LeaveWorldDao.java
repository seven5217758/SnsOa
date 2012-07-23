package com.SnsOa.entity.portle.cargo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.portle.cargo.LeaveWorld;
@Repository("LeaveWorldDao")
public interface LeaveWorldDao {
	public boolean save(LeaveWorld comObj);
	public boolean update(LeaveWorld comObj);
	public boolean delete(long id);
	public List<LeaveWorld> query(String sqlstr);
	public LeaveWorld getCompany(long id);
	public List<LeaveWorld> queryPage(String sqlstr,int nowpage,int maxRow);
}
