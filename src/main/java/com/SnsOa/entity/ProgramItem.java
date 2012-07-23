package com.SnsOa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProgramItem {
	private long id;
	private long programId;
	private String itemName;
	private String appFile;
	private String appClass;
	private String appId;
	private boolean isEnable;
	private String icon;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public long getProgramId() {
		return programId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getItemName() {
		return itemName;
	}
	public String getAppFile() {
		return appFile;
	}
	public String getAppClass() {
		return appClass;
	}
	public String getAppId() {
		return appId;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setProgramId(long programId) {
		this.programId = programId;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setAppFile(String appFile) {
		this.appFile = appFile;
	}
	public void setAppClass(String appClass) {
		this.appClass = appClass;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	
}
