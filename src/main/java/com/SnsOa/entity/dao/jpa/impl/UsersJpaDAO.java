package com.SnsOa.entity.dao.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.SnsOa.entity.Users;
import com.SnsOa.entity.dao.UsersDAO;
@Repository("UsersJpaDAO")
public class UsersJpaDAO implements UsersDAO {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(UsersJpaDAO.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		logger.info("删除指定users信息,usersId是:"+id);
		boolean b=true;
		try{
		Users users=entity.find(Users.class,id);
		entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public List<Users> query(String sqlstr) {
		// TODO Auto-generated method stub
		logger.info("执行sql:"+sqlstr);
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(Users comObj) {
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
	public boolean update(Users comObj) {
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
	public Users getUsers(long id) {
		// TODO Auto-generated method stub
		return entity.find(Users.class,id);
	}
}
