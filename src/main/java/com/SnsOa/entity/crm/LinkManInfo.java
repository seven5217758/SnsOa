package com.SnsOa.entity.crm;



import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@javax.persistence.Entity
public class LinkManInfo {
	private long id;
	private long primaryManId;
	private String fname;
	private String lname;
	private int sex;
	private long clientName;
	private String job;
	private String phone;
	private String tel;
	private String email;
	private long superior;
	private String sendMailAddressCountry;
	private String sendMailAddressCode;
	private String sendMailAddressProvince;
	private String sendMailAddressCity;
	private String sendMailAddressStreet;
	private String otherSendMailAddressCountry;
	private String otherSendMailAddressCode;
	private String otherSendMailAddressProvince;
	private String otherSendMailAddressCity;
	private String otherSendMailAddressStreet;
	private String fax;
	private String HomeTel;
	private String otherTel;
	private String assistent;
	private String assistentTel;
	private long clientSource;
	private Date deteOfbirth;
	private String marks;
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	private long companyId;
	private Date dataDate;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public long getPrimaryManId() {
		return primaryManId;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public int getSex() {
		return sex;
	}
	public long getClientName() {
		return clientName;
	}
	public String getJob() {
		return job;
	}
	public String getPhone() {
		return phone;
	}
	public String getTel() {
		return tel;
	}
	public String getEmail() {
		return email;
	}
	public long getSuperior() {
		return superior;
	}
	public String getSendMailAddressCountry() {
		return sendMailAddressCountry;
	}
	public String getSendMailAddressCode() {
		return sendMailAddressCode;
	}
	public String getSendMailAddressProvince() {
		return sendMailAddressProvince;
	}
	public String getSendMailAddressCity() {
		return sendMailAddressCity;
	}
	public String getSendMailAddressStreet() {
		return sendMailAddressStreet;
	}
	public String getOtherSendMailAddressCountry() {
		return otherSendMailAddressCountry;
	}
	public String getOtherSendMailAddressCode() {
		return otherSendMailAddressCode;
	}
	public String getOtherSendMailAddressProvince() {
		return otherSendMailAddressProvince;
	}
	public String getOtherSendMailAddressCity() {
		return otherSendMailAddressCity;
	}
	public String getOtherSendMailAddressStreet() {
		return otherSendMailAddressStreet;
	}
	public String getFax() {
		return fax;
	}
	public String getHomeTel() {
		return HomeTel;
	}
	public String getOtherTel() {
		return otherTel;
	}
	public String getAssistent() {
		return assistent;
	}
	public String getAssistentTel() {
		return assistentTel;
	}
	public long getClientSource() {
		return clientSource;
	}
	public Date getDeteOfbirth() {
		return deteOfbirth;
	}
	public String getMarks() {
		return marks;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPrimaryManId(long primaryManId) {
		this.primaryManId = primaryManId;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public void setClientName(long clientName) {
		this.clientName = clientName;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSuperior(long superior) {
		this.superior = superior;
	}
	public void setSendMailAddressCountry(String sendMailAddressCountry) {
		this.sendMailAddressCountry = sendMailAddressCountry;
	}
	public void setSendMailAddressCode(String sendMailAddressCode) {
		this.sendMailAddressCode = sendMailAddressCode;
	}
	public void setSendMailAddressProvince(String sendMailAddressProvince) {
		this.sendMailAddressProvince = sendMailAddressProvince;
	}
	public void setSendMailAddressCity(String sendMailAddressCity) {
		this.sendMailAddressCity = sendMailAddressCity;
	}
	public void setSendMailAddressStreet(String sendMailAddressStreet) {
		this.sendMailAddressStreet = sendMailAddressStreet;
	}
	public void setOtherSendMailAddressCountry(String otherSendMailAddressCountry) {
		this.otherSendMailAddressCountry = otherSendMailAddressCountry;
	}
	public void setOtherSendMailAddressCode(String otherSendMailAddressCode) {
		this.otherSendMailAddressCode = otherSendMailAddressCode;
	}
	public void setOtherSendMailAddressProvince(String otherSendMailAddressProvince) {
		this.otherSendMailAddressProvince = otherSendMailAddressProvince;
	}
	public void setOtherSendMailAddressCity(String otherSendMailAddressCity) {
		this.otherSendMailAddressCity = otherSendMailAddressCity;
	}
	public void setOtherSendMailAddressStreet(String otherSendMailAddressStreet) {
		this.otherSendMailAddressStreet = otherSendMailAddressStreet;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public void setHomeTel(String homeTel) {
		HomeTel = homeTel;
	}
	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}
	public void setAssistent(String assistent) {
		this.assistent = assistent;
	}
	public void setAssistentTel(String assistentTel) {
		this.assistentTel = assistentTel;
	}
	public void setClientSource(long clientSource) {
		this.clientSource = clientSource;
	}
	public void setDeteOfbirth(Date deteOfbirth) {
		this.deteOfbirth = deteOfbirth;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
}
