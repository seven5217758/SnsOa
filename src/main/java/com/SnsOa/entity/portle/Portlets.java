package com.SnsOa.entity.portle;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Portlets {
	private long id;
	private String title;
	private String url;
	private String extId;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public String getExtId() {
		return extId;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setExtId(String extId) {
		this.extId = extId;
	}
	
}
