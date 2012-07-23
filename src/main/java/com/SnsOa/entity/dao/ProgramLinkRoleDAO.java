package com.SnsOa.entity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.ProgramLinkRole;
@Repository(value="ProgramLinkRoleDAO")
public interface ProgramLinkRoleDAO {
	public boolean save(ProgramLinkRole comObj);
	public boolean update(ProgramLinkRole comObj);
	public boolean delete(long id);
	public List<ProgramLinkRole> query(String sqlstr);
	public ProgramLinkRole getProgramLinkRoles(long id);
}
