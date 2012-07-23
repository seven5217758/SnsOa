package com.SnsOa.entity.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.Company;
/**
 * 管理company 接口
 * @author Thinkpad
 *
 */
@Repository("CompanyDAO")
public interface CompanyDAO {
	public boolean save(Company comObj);
	public boolean update(Company comObj);
	public boolean delete(long id);
	public List<Company> query(String sqlstr);
	public Company getCompany(long id);
}
