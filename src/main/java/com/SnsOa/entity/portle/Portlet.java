package com.SnsOa.entity.portle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Portlet {
	private long id;
	private long rowNum;
	private long columnNum;
	private long pageId;
	private long portletId;
	private long companyId;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public long getRowNum() {
		return rowNum;
	}
	public long getPageId() {
		return pageId;
	}
	public long getColumnNum() {
		return columnNum;
	}
	public void setColumnNum(long columnNum) {
		this.columnNum = columnNum;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setRowNum(long rowNum) {
		this.rowNum = rowNum;
	}
	public void setPageId(long pageId) {
		this.pageId = pageId;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getPortletId() {
		return portletId;
	}
	public void setPortletId(long portletId) {
		this.portletId = portletId;
	}
	
}
