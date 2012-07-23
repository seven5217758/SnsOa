package com.SnsOa.entity.crm.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.crm.ClientSource;
import com.SnsOa.entity.crm.LinkManInfo;
import com.SnsOa.entity.crm.dao.ClientSourceDAO;
@Repository("ClientSourceJpaDAO")
public class ClientSourceJpaDAO implements ClientSourceDAO {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(ClientSourceJpaDAO.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			ClientSource users=entity.find(ClientSource.class,id);
			entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public ClientSource getClientSource(long id) {
		// TODO Auto-generated method stub
		return entity.find(ClientSource.class,id);
	}

	@Override
	public List<ClientSource> query(String sqlstr) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(ClientSource comObj) {
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
	public boolean update(ClientSource comObj) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			entity.merge(comObj);
		}catch(Exception ex){
			b=false;
		}
		return b;
	}

}
