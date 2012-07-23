package com.SnsOa.entity.crm.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.crm.ClientCompany;
import com.SnsOa.entity.crm.ClientSource;
import com.SnsOa.entity.crm.LinkManInfo;
import com.SnsOa.entity.crm.dao.ClientCompanyDAO;
@Repository("ClientCompanyJpaDAO")
public class ClientCompanyJpaDAO implements ClientCompanyDAO {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(ClientCompanyJpaDAO.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			ClientCompany users=entity.find(ClientCompany.class,id);
			entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public ClientCompany getClientCompany(long id) {
		// TODO Auto-generated method stub
		return entity.find(ClientCompany.class,id);
	}

	@Override
	public List<ClientCompany> query(String sqlstr) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(ClientCompany comObj) {
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
	public boolean update(ClientCompany comObj) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			entity.merge(comObj);
		}catch(Exception ex){
			b=false;
		}
		return b;
	}
	@Override
	public List<ClientCompany> queryPage(String sqlstr, int nowpage, int maxRow) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).setFirstResult(nowpage).setMaxResults(maxRow).getResultList();
	}
}
