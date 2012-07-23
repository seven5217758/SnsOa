package com.SnsOa.entity.crm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClientSource {
	private long id;
	private String name;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
