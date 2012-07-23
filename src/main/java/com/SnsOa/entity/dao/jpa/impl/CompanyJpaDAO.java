package com.SnsOa.entity.dao.jpa.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.Company;
import com.SnsOa.entity.Users;
import com.SnsOa.entity.dao.CompanyDAO;
import com.SnsOa.spring.controller.HomeController;
@Repository("CompanyJpaDAO")
public class CompanyJpaDAO implements CompanyDAO {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(CompanyJpaDAO.class);
	public boolean delete(long id) {
		
		// TODO Auto-generated method stub
		Query query=entity.createQuery("select * from Company where companyId="+id);
		Iterator<Company> i=query.getResultList().iterator();
		boolean b=true;
		if(i.hasNext()){
			entity.remove(i.next());
		}else{
			b=false;
		}
		return false;
	}

	public List<Company> query(String sqlstr) {
		logger.info("自定义查询:"+sqlstr);
		// TODO Auto-generated method stub
		Query query=entity.createQuery(sqlstr);
		return query.getResultList();
	}

	public boolean save(Company comObj) {
		// TODO Auto-generated method stub
		logger.info("保存公司对象:"+comObj);
		boolean b=true;
		try{
			entity.persist(comObj);
		}catch(Exception ex){
			b=false;
		}
		return b;
	}

	public boolean update(Company comObj) {
		// TODO Auto-generated method stub
		logger.info("更新公司对象:"+comObj);
		boolean b=true;
		try{
			entity.merge(comObj);
		}catch(Exception ex){
			b=false;
		}
		return b;
	}

	@Override
	public Company getCompany(long id) {
		// TODO Auto-generated method stub
		return entity.find(Company.class,id);
	}

}
