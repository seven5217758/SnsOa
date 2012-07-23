package com.SnsOa.entity.portle.cargo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class LeaveWorld {
	private long id;
	private String msg;
	private Date date;
	private long cuserid;
	private long companyid;
	private long cargoid;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getMsg() {
		return msg;
	}
	public Date getDate() {
		return date;
	}
	public long getCuserid() {
		return cuserid;
	}
	public long getCompanyid() {
		return companyid;
	}
	public long getCargoid() {
		return cargoid;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setCuserid(long cuserid) {
		this.cuserid = cuserid;
	}
	public void setCompanyid(long companyid) {
		this.companyid = companyid;
	}
	public void setCargoid(long cargoid) {
		this.cargoid = cargoid;
	}
	
}
