package com.SnsOa.entity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.Program;
@Repository("ProgramDAO")
public interface ProgramDAO {
	public boolean save(Program proObj);
	public boolean update(Program proObj);
	public boolean delete(long id);
	public List<Program> query(String sqlstr);
	public Program getProgram(long id);
}
