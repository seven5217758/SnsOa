package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CompanyQuale {
	private long id;
	private long companyId;
	private long indexs;
	private long companyTypeId;
	private String marks;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public long getCompanyId() {
		return companyId;
	}
	public long getIndexs() {
		return indexs;
	}
	public void setIndexs(long indexs) {
		this.indexs = indexs;
	}
	public long getCompanyTypeId() {
		return companyTypeId;
	}
	public String getMarks() {
		return marks;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setCompanyTypeId(long companyTypeId) {
		this.companyTypeId = companyTypeId;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	
}
