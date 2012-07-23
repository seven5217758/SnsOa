package com.SnsOa.entity.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.crm.LinkManInfo;
@Repository("LinkManInfoDAO")
public interface LinkManInfoDAO {
	public boolean save(LinkManInfo comObj);
	public boolean update(LinkManInfo comObj);
	public boolean delete(long id);
	public List<LinkManInfo> query(String sqlstr);
	public LinkManInfo getLinkManInfo(long id);
	public List<LinkManInfo> queryPage(String sqlstr,int nowpage,int maxRow);
}
