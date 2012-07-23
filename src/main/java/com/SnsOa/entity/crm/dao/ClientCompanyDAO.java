package com.SnsOa.entity.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.crm.ClientCompany;
@Repository("ClientCompanyDAO")
public interface ClientCompanyDAO {
	public boolean save(ClientCompany comObj);
	public boolean update(ClientCompany comObj);
	public boolean delete(long id);
	public List<ClientCompany> query(String sqlstr);
	public ClientCompany getClientCompany(long id);
	public List<ClientCompany> queryPage(String sqlstr,int nowpage,int maxRow);
}
