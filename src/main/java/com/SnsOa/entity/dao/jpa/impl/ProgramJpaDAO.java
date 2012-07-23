package com.SnsOa.entity.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.Program;
import com.SnsOa.entity.Users;
import com.SnsOa.entity.dao.ProgramDAO;
@Repository("ProgramJpaDAO")
public class ProgramJpaDAO implements ProgramDAO {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(ProgramJpaDAO.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		logger.info("删除指定program信息,program是:"+id);
		boolean b=true;
		try{
			Program users=entity.find(Program.class,id);
		entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return false;
	}

	@Override
	public List<Program> query(String sqlstr) {
		// TODO Auto-generated method stub
		logger.info("执行sql:"+sqlstr);
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(Program proObj) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			entity.persist(proObj);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public boolean update(Program proObj) {
		// TODO Auto-generated method stub
		logger.info("更新program对象:"+proObj);
		boolean b=true;
		try{
			entity.merge(proObj);
		}catch(Exception ex){
			b=false;
		}
		return b;
	}

	@Override
	public Program getProgram(long id) {
		// TODO Auto-generated method stub
		return entity.find(Program.class,id);
	}

}
