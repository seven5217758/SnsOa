package com.SnsOa.entity.service.cargo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SnsOa.entity.portle.Portlet;
import com.SnsOa.entity.portle.PortletPage;
import com.SnsOa.entity.portle.Portlets;
import com.SnsOa.entity.portle.cargo.dao.CargoDao;
import com.SnsOa.entity.portle.cargo.dao.CouponDao;
import com.SnsOa.entity.portle.cargo.dao.CusersDao;
import com.SnsOa.entity.portle.cargo.dao.LeaveWorldDao;
import com.SnsOa.entity.portle.dao.PortletDao;
import com.SnsOa.entity.portle.dao.PortletPageDao;
import com.SnsOa.entity.portle.dao.PortletsDao;
import com.SnsOa.entity.service.cargo.CargoService;
@Service("CargoServiceImpl")
@Transactional
public class CargoServiceImpl implements CargoService {
	@Resource(name="CargoJpaDao")
	private CargoDao cargodao;
	@Resource(name="CouponJpaDao")
	private CouponDao coupondao;
	@Resource(name="CusersJpaDao")
	private CusersDao cusers;
	@Resource(name="LeaveWorldJpaDao")
	private LeaveWorldDao leave;
	@Resource(name="PortletJpaDao")
	private PortletDao portletdao;
	@Resource(name="PortletsJpaDao")
	private PortletsDao portletsdao;
	@Resource(name="PortletPageJpaDao")
	private PortletPageDao portletPageDao;
	/**
	 * 查询一个公司的一个程序所有页面
	 */
	public List<PortletPage> getPortletPageList(long appid,long companyid){
		List<PortletPage> pageList=portletPageDao.query("from PortletPage where appId ="+appid+" and companyId="+companyid);
		return pageList;
	}
	/**
	 * 获取一个页面的所有portlet
	 */
	public List<Portlet> getPagePortletToAll(long pageId,long companyId){
		List<Portlet> list=portletdao.query("from Portlet where pageId="+pageId+" and companyId="+companyId);
		return list;
	}
	@Override
	public List<Portlets> getPortletAll(long appid) {
		// TODO Auto-generated method stub
		return null;
	}
}
