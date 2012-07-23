package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProgramLinkCompany {
	private long id;
	private long companyId;
	private long programId;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public long getCompanyId() {
		return companyId;
	}
	public long getProgramId() {
		return programId;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public void setProgramId(long programId) {
		this.programId = programId;
	}
}
