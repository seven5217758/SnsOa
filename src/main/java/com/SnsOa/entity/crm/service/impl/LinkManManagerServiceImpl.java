package com.SnsOa.entity.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SnsOa.entity.crm.ClientCompany;
import com.SnsOa.entity.crm.ClientSource;
import com.SnsOa.entity.crm.LinkManInfo;
import com.SnsOa.entity.crm.dao.ClientCompanyDAO;
import com.SnsOa.entity.crm.dao.ClientSourceDAO;
import com.SnsOa.entity.crm.dao.LinkManInfoDAO;
import com.SnsOa.entity.crm.service.LinkManManagerService;
@Service("LinkManManagerServiceImpl")
@Transactional
public class LinkManManagerServiceImpl implements LinkManManagerService {
	@Resource(name="ClientSourceJpaDAO")
	private ClientSourceDAO client; 
	@Resource(name="LinkManInfoJpaDAO")
	private LinkManInfoDAO linkman; 
	@Resource(name="ClientCompanyJpaDAO")
	private ClientCompanyDAO clientcompany; 
	@Override
	public List<LinkManInfo> getLinkManList(String sql) {
		// TODO Auto-generated method stub
		return linkman.query(sql);
	}

	@Override
	public boolean addNewLinkManInfo(LinkManInfo link) {
		// TODO Auto-generated method stub
		return linkman.save(link);
	}

	@Override
	public List<ClientSource> getAllClientSource() {
		// TODO Auto-generated method stub
		return client.query("from ClientSource");
	}

	@Override
	public boolean removeLinkManInfo(long id) {
		// TODO Auto-generated method stub
		return linkman.delete(id);
	}

	@Override
	public boolean updateLinkManInfo(LinkManInfo link) {
		// TODO Auto-generated method stub
		return linkman.update(link);
	}

	@Override
	public ClientSource getClientSourceItem(long id) {
		// TODO Auto-generated method stub
		return client.getClientSource(id);
	}

	@Override
	public List<LinkManInfo> getPartPageLinkManList(String sql,int nowpage, int maxRow) {
		// TODO Auto-generated method stub
		return linkman.queryPage(sql, nowpage, maxRow);
	}


	@Override
	public boolean saveClientCompany(ClientCompany clientCompany) {
		// TODO Auto-generated method stub
		return clientcompany.save(clientCompany);
	}

	@Override
	public List<ClientCompany> getClientCompany(String sql, int maxCountRow,
			int nowPageCount) {
		// TODO Auto-generated method stub
		return clientcompany.queryPage(sql, nowPageCount, maxCountRow);
	}

	@Override
	public ClientCompany getClientCompany(long id) {
		// TODO Auto-generated method stub
		return clientcompany.getClientCompany(id);
	}

	@Override
	public LinkManInfo getLinkManInfo(long id) {
		// TODO Auto-generated method stub
		return linkman.getLinkManInfo(id);
	}
}
