package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * 公司类型
 */
@Entity
public class CompanyType {
	private long id;
	private String typeName;
	private String indexs;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getIndexs() {
		return indexs;
	}
	public void setIndexs(String indexs) {
		this.indexs = indexs;
	}
}
