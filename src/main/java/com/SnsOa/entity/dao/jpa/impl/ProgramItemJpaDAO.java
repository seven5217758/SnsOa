package com.SnsOa.entity.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.dao.ProgramItemDAO;
@Repository("ProgramItemJpaDAO")
public class ProgramItemJpaDAO implements ProgramItemDAO {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(ProgramItemJpaDAO.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		logger.info("ɾ��ָ��programItem��Ϣ,programitem��:"+id);
		boolean b=true;
		try{
			ProgramItem users=entity.find(ProgramItem.class,id);
		entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
		
	}

	@Override
	public List<ProgramItem> query(String sqlstr) {
		// TODO Auto-generated method stub
		logger.info("ִ��sql:"+sqlstr);
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(ProgramItem comObj) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			entity.persist(comObj);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public boolean update(ProgramItem comObj) {
		// TODO Auto-generated method stub
		logger.info("����programitem����:"+comObj);
		boolean b=true;
		try{
			entity.merge(comObj);
		}catch(Exception ex){
			b=false;
		}
		return b;
	}

	@Override
	public ProgramItem getProgramItem(long id) {
		// TODO Auto-generated method stub
		return entity.find(ProgramItem.class,id);
	}

}
