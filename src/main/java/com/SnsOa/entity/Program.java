package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Program {
	private long id;
	private String programName;
	private long indexs;
	private long useNum;
	private String url;
	private boolean isEnable;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getProgramName() {
		return programName;
	}

	public long getUseNum() {
		return useNum;
	}
	public String getUrl() {
		return url;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public void setUseNum(long useNum) {
		this.useNum = useNum;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getIndexs() {
		return indexs;
	}
	public void setIndexs(long indexs) {
		this.indexs = indexs;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	
}
