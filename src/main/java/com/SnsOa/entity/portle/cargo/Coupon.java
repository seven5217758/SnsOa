package com.SnsOa.entity.portle.cargo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Coupon {
	private long id;
	private String name;
	private String marks;
	private float discount;
	private Date beginDate;
	private Date endDate;
	private long companyid;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getMarks() {
		return marks;
	}
	public float getDiscount() {
		return discount;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public long getCompanyid() {
		return companyid;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setCompanyid(long companyid) {
		this.companyid = companyid;
	}
	
}
