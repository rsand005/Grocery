package com.DailyGroceryShop.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Category implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + "]";
	}

	public Category() {}
	
	
	public Category(long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	
	

	
	
	
}
