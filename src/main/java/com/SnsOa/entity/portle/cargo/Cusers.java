package com.SnsOa.entity.portle.cargo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cusers {
	private long id;
	private String account;
	private String password;
	private String name;
	private int sex;
	private String phone;
	private String qq;
	private String email;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getAccount() {
		return account;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public int getSex() {
		return sex;
	}
	public String getPhone() {
		return phone;
	}
	public String getQq() {
		return qq;
	}
	public String getEmail() {
		return email;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
