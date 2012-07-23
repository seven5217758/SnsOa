package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * ¹«Ë¾
 * @author Thinkpad
 *
 */
@Entity
public class Company {
	private long companyId;
	private String companyName;
	private long companyType;
	private String businessLicenselmage;
	private String corporateMan;
	private int registerMan;
	private String registerfund;
	@Id
	@GeneratedValue
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public long getCompanyType() {
		return companyType;
	}
	public void setCompanyType(long companyType) {
		this.companyType = companyType;
	}
	public String getBusinessLicenselmage() {
		return businessLicenselmage;
	}
	public void setBusinessLicenselmage(String businessLicenselmage) {
		this.businessLicenselmage = businessLicenselmage;
	}
	public String getCorporateMan() {
		return corporateMan;
	}
	public void setCorporateMan(String corporateMan) {
		this.corporateMan = corporateMan;
	}
	public String getRegisterfund() {
		return registerfund;
	}
	public void setRegisterfund(String registerfund) {
		this.registerfund = registerfund;
	}
	
}
