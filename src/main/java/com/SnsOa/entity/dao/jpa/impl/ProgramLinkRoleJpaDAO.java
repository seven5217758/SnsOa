package com.SnsOa.entity.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.ProgramLinkCompany;
import com.SnsOa.entity.ProgramLinkRole;
import com.SnsOa.entity.dao.ProgramLinkRoleDAO;
@Repository(value="ProgramLinkRoleJpaDAO")
public class ProgramLinkRoleJpaDAO implements ProgramLinkRoleDAO {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(ProgramLinkCompanyJpaDAO.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		logger.info("删除指定ProgramLinkRoleJpaDAO信息,ProgramLinkRoleJpaDAO是:"+id);
		boolean b=true;
		try{
			ProgramLinkRole company=entity.find(ProgramLinkRole.class,id);
			entity.remove(company);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}
	@Override
	public List<ProgramLinkRole> query(String sqlstr) {
		// TODO Auto-generated method stub
		logger.info("执行sql:"+sqlstr);
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(ProgramLinkRole comObj) {
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
	public boolean update(ProgramLinkRole comObj) {
		// TODO Auto-generated method stub
		logger.info("更新ProgramLinkRole对象:"+comObj);
		boolean b=true;
		try{
			entity.merge(comObj);
		}catch(Exception ex){
			b=false;
		}
		return b;
	}


	@Override
	public ProgramLinkRole getProgramLinkRoles(long id) {
		// TODO Auto-generated method stub
		return entity.find(ProgramLinkRole.class,id);
	}

}
