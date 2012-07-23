package com.SnsOa.entity.crm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClientCompany {
	private long id;
	private String clientCompanyName;
	private long companyId;
	private Date dataDate;
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getClientCompanyName() {
		return clientCompanyName;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setClientCompanyName(String clientCompanyName) {
		this.clientCompanyName = clientCompanyName;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
}
