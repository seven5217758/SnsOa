package com.SnsOa.entity.portle.cargo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cargo {
	private long id;
	private String name;
	private String price;
	private String marks;
	private long num;
	private long companyid;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public String getMarks() {
		return marks;
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
	public void setPrice(String price) {
		this.price = price;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public void setCompanyid(long companyid) {
		this.companyid = companyid;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	
}
