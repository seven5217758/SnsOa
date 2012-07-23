package com.SnsOa.entity.portle.cargo.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.crm.LinkManInfo;
import com.SnsOa.entity.portle.cargo.Cargo;
import com.SnsOa.entity.portle.cargo.Coupon;
import com.SnsOa.entity.portle.cargo.dao.CouponDao;
@Repository("CouponJpaDao")
public class CouponJpaDao implements CouponDao {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(CouponJpaDao.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			Coupon users=entity.find(Coupon.class,id);
			entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public Coupon getCompany(long id) {
		// TODO Auto-generated method stub
		return entity.find(Coupon.class,id);
	}

	@Override
	public List<Coupon> query(String sqlstr) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(Coupon comObj) {
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
	public boolean update(Coupon comObj) {
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
	public List<Coupon> queryPage(String sqlstr, int nowpage, int maxRow) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).setFirstResult(nowpage).setMaxResults(maxRow).getResultList();
	}

}
