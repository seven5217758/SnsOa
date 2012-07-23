package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Roles {
	private long id;
	private long companyId;
	private String name;
	private String marks;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public long getCompanyId() {
		return companyId;
	}
	public String getName() {
		return name;
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
	public void setName(String name) {
		this.name = name;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	
}
