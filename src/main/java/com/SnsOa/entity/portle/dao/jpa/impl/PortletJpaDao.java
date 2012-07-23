package com.SnsOa.entity.portle.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.portle.Portlet;
import com.SnsOa.entity.portle.cargo.Cargo;
import com.SnsOa.entity.portle.dao.PortletDao;
@Repository("PortletJpaDao")
public class PortletJpaDao implements PortletDao{
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(PortletJpaDao.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			Portlet users=entity.find(Portlet.class,id);
			entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public Portlet getPortlet(long id) {
		// TODO Auto-generated method stub
		return entity.find(Portlet.class,id);
	}

	@Override
	public List<Portlet> query(String sqlstr) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public List<Portlet> queryPage(String sqlstr, int nowpage, int maxRow) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).setFirstResult(nowpage).setMaxResults(maxRow).getResultList();
	}

	@Override
	public boolean save(Portlet comObj) {
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
	public boolean update(Portlet comObj) {
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
