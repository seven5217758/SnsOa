package com.SnsOa.entity.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.Roles;
import com.SnsOa.entity.dao.RolesDao;
@Repository("RolesJpaDAO")
public class RolesJpaDAO implements RolesDao {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(RolesJpaDAO.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			Roles users=entity.find(Roles.class,id);
			entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public Roles getRoles(long id) {
		// TODO Auto-generated method stub
		return entity.find(Roles.class,id);
	}

	@Override
	public List<Roles> query(String sqlstr) {
		// TODO Auto-generated method stub
		logger.info("执行sql:"+sqlstr);
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(Roles roles) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			entity.persist(roles);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public boolean update(Roles roles) {
		// TODO Auto-generated method stub
		logger.info("更新角色对象:"+roles);
		boolean b=true;
		try{
			entity.merge(roles);
		}catch(Exception ex){
			b=false;
		}
		return b;
	}

}
