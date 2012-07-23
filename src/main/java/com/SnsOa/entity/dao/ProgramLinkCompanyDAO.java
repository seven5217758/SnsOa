package com.SnsOa.entity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.ProgramLinkCompany;
@Repository("ProgramLinkCompanyDAO")
public interface ProgramLinkCompanyDAO {
	public boolean save(ProgramLinkCompany comObj);
	public boolean update(ProgramLinkCompany comObj);
	public boolean delete(long id);
	public List<ProgramLinkCompany> query(String sqlstr);
	public ProgramLinkCompany getProgramLinkCompany(long id);
}
