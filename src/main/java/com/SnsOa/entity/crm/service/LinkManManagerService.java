package com.SnsOa.entity.crm.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.crm.ClientCompany;
import com.SnsOa.entity.crm.ClientSource;
import com.SnsOa.entity.crm.LinkManInfo;
@Repository("LinkManManagerService")
public interface LinkManManagerService {
	public List<LinkManInfo> getLinkManList(String sql);
	public List<LinkManInfo> getPartPageLinkManList(String sql,int nowpage,int maxRow);
	public List<ClientSource> getAllClientSource();
	public boolean addNewLinkManInfo(LinkManInfo link);
	public boolean removeLinkManInfo(long id);
	public boolean updateLinkManInfo(LinkManInfo link);
	public ClientSource getClientSourceItem(long id);
	public List<ClientCompany> getClientCompany(String sql,int maxCountRow,int nowPageCount);
	public boolean saveClientCompany(ClientCompany clientCompany);
	public ClientCompany getClientCompany(long id);
	public LinkManInfo getLinkManInfo(long id);
}