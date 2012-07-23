package com.SnsOa.entity.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.crm.ClientSource;
@Repository("ClientSourceDAO")
public interface ClientSourceDAO {
	public boolean save(ClientSource comObj);
	public boolean update(ClientSource comObj);
	public boolean delete(long id);
	public List<ClientSource> query(String sqlstr);
	public ClientSource getClientSource(long id);
}
