package com.SnsOa.entity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.ProgramItem;
@Repository("ProgramItemDAO")
public interface ProgramItemDAO {
	public boolean save(ProgramItem comObj);
	public boolean update(ProgramItem comObj);
	public boolean delete(long id);
	public List<ProgramItem> query(String sqlstr);
	public ProgramItem getProgramItem(long id);
}
