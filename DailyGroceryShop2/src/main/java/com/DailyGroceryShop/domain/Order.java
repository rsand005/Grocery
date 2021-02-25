package com.DailyGroceryShop.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="orders")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int orderId;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate orderDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate deliveryDate;
	private float deliveryCharge;
	private String status;
	
	@OneToOne
	private Customer customer;
	
	@OneToMany(cascade=CascadeType.ALL)
	public List<OrderedProd> orderedProductList;

	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public float getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderedProd> getOrderedProductList() {
		return orderedProductList;
	}

	public void setOrderedProductList(List<OrderedProd> orderedProductList) {
		this.orderedProductList = orderedProductList;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate
				+ ", deliveryCharge=" + deliveryCharge + ", status=" + status + ", customer=" + customer
				+ ", orderedProductList=" + orderedProductList + "]";
	}
	
	
}
