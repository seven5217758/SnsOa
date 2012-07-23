package com.SnsOa.entity.service.cargo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SnsOa.entity.portle.Portlet;
import com.SnsOa.entity.portle.PortletPage;
import com.SnsOa.entity.portle.Portlets;

@Service("CargoService")
public interface CargoService {
	public List<PortletPage> getPortletPageList(long appid,long companyid);
	public List<Portlet> getPagePortletToAll(long pageId,long companyId);
	public List<Portlets> getPortletAll(long appid);
}