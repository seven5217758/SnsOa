package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 公司业务联系方式
 * @author Thinkpad
 *
 */
@Entity
public class CompanyLinkFunction {
	private long companyId;
	private String telNumber;
	private String address;
	private String postCode;
	private String email_hr;
	private String email_main;
	@Id
	@GeneratedValue
	public long getCompanyId() {
		return companyId;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public String getAddress() {
		return address;
	}
	public String getPostCode() {
		return postCode;
	}
	public String getEmail_hr() {
		return email_hr;
	}
	public String getEmail_main() {
		return email_main;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public void setEmail_hr(String emailHr) {
		email_hr = emailHr;
	}
	public void setEmail_main(String emailMain) {
		email_main = emailMain;
	}
	
}
