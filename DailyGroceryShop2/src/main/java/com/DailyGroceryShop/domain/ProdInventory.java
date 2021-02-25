package com.DailyGroceryShop.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class ProdInventory implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int quantity;
	private float discount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public ProdInventory() {}
	public ProdInventory(long id, int quantity, float discount) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.discount = discount;
	}
	
	public ProdInventory(int quantity, float discount) {
		this.quantity = quantity;
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "ProdInventory [id=" + id + ", quantity=" + quantity + ", discount=" + discount + "]";
	}
	
	
	
	
}
