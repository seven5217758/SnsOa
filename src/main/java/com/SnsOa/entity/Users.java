package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *  用户表
 * @author Thinkpad
 *
 */
@Entity
public class Users {
	private long id;
	private long role;
	private String account;
	private String password;
	private long companyId;
	private String email;
	private String fname;//姓
	private String lname;//名
	private boolean changerAccount;
	private boolean mainAccount;//是主账户，就是注册公司时生成的账户
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRole() {
		return role;
	}
	public void setRole(long role) {
		this.role = role;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isChangerAccount() {
		return changerAccount;
	}
	public void setChangerAccount(boolean changerAccount) {
		changerAccount = changerAccount;
	}
	public boolean isMainAccount() {
		return mainAccount;
	}
	public void setMainAccount(boolean mainAccount) {
		this.mainAccount = mainAccount;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
}
