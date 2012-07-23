package com.SnsOa.entity.portle.cargo.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.portle.cargo.Cargo;
import com.SnsOa.entity.portle.cargo.dao.CargoDao;
@Repository("CargoJpaDao")
public class CargoJpaDao implements CargoDao {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(CargoJpaDao.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			Cargo users=entity.find(Cargo.class,id);
			entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public Cargo getCompany(long id) {
		// TODO Auto-generated method stub
		return entity.find(Cargo.class,id);
	}

	@Override
	public List<Cargo> query(String sqlstr) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(Cargo comObj) {
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
	public boolean update(Cargo comObj) {
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
	public List<Cargo> queryPage(String sqlstr, int nowpage, int maxRow) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).setFirstResult(nowpage).setMaxResults(maxRow).getResultList();
	}

}
