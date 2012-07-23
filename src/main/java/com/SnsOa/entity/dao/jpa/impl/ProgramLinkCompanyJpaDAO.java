package com.SnsOa.entity.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.ProgramLinkCompany;
import com.SnsOa.entity.dao.ProgramLinkCompanyDAO;
@Repository("ProgramLinkCompanyJpaDAO")
public class ProgramLinkCompanyJpaDAO implements ProgramLinkCompanyDAO {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(ProgramLinkCompanyJpaDAO.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		logger.info("删除指定ProgramLinkCompany信息,ProgramLinkCompanyid是:"+id);
		boolean b=true;
		try{
			ProgramLinkCompany company=entity.find(ProgramLinkCompany.class,id);
			entity.remove(company);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public ProgramLinkCompany getProgramLinkCompany(long id) {
		// TODO Auto-generated method stub
		return entity.find(ProgramLinkCompany.class,id);
	}

	@Override
	public List<ProgramLinkCompany> query(String sqlstr) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(ProgramLinkCompany comObj) {
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
	public boolean update(ProgramLinkCompany comObj) {
		// TODO Auto-generated method stub
		logger.info("更新ProgramLinkCompany对象:"+comObj);
		boolean b=true;
		try{
			entity.merge(comObj);
		}catch(Exception ex){
			b=false;
		}
		return b;
	}

}
