package com.SnsOa.entity.portle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PortletPage {
	private long id;
	private String name;
	private long appId;
	private long columnNum;
	private long companyId;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public long getAppId() {
		return appId;
	}
	public long getColumnNum() {
		return columnNum;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public void setColumnNum(long columnNum) {
		this.columnNum = columnNum;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
}
