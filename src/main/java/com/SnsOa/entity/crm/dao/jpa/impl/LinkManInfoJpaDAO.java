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
import com.SnsOa.entity.crm.dao.LinkManInfoDAO;
@Repository("LinkManInfoJpaDAO")
public class LinkManInfoJpaDAO implements LinkManInfoDAO {
	@PersistenceContext
	private EntityManager entity;
	private static final Logger logger = LoggerFactory
	.getLogger(LinkManInfoJpaDAO.class);
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean b=true;
		try{
			LinkManInfo users=entity.find(LinkManInfo.class,id);
			entity.remove(users);
		}catch(Exception ex){
			logger.info(ex.getMessage());
			b=false;
		}
		return b;
	}

	@Override
	public LinkManInfo getLinkManInfo(long id) {
		// TODO Auto-generated method stub
		return entity.find(LinkManInfo.class,id);
	}

	@Override
	public List<LinkManInfo> query(String sqlstr) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).getResultList();
	}

	@Override
	public boolean save(LinkManInfo comObj) {
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
	public boolean update(LinkManInfo comObj) {
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
	public List<LinkManInfo> queryPage(String sqlstr, int nowpage, int maxRow) {
		// TODO Auto-generated method stub
		return entity.createQuery(sqlstr).setFirstResult(nowpage).setMaxResults(maxRow).getResultList();
	}

}
