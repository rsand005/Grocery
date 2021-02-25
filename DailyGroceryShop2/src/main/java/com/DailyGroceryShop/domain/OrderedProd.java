package com.DailyGroceryShop.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class OrderedProd implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	public Product product;
	
	private int quantity;
	private float cost;
	private float discount;
	private float finalCost;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getFinalCost() {
		return finalCost;
	}
	public void setFinalCost(float finalCost) {
		this.finalCost = finalCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OrderedProd() {}
	
	public OrderedProd(int id, Product product, int quantity, float cost, float discount, float finalCost,
			String status) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.cost = cost;
		this.discount = discount;
		this.finalCost = finalCost;
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrderedProd [id=" + id + ", product=" + product + ", quantity=" + quantity + ", cost=" + cost
				+ ", discount=" + discount + ", finalCost=" + finalCost + ", status=" + status + "]";
	}
	
	
}
