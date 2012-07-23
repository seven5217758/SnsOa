package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProgramLinkRole {
	private long id;
	private long rolesId;
	private long programItemId;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public long getRolesId() {
		return rolesId;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setRolesId(long rolesId) {
		this.rolesId = rolesId;
	}
	public long getProgramItemId() {
		return programItemId;
	}
	public void setProgramItemId(long programItemId) {
		this.programItemId = programItemId;
	}
	
}
