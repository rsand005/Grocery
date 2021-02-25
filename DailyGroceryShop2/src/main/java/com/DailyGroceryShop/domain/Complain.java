package com.DailyGroceryShop.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="complains")
public class Complain implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private LocalDate complainRaisedDate;
	private String description;
	private String status;
	private LocalDate complainClosedDate;
	private String complainType;
	
	@OneToOne
	public Order order;
	
	@OneToMany
	List<OrderedProd> orderedProd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getComplainRaisedDate() {
		return complainRaisedDate;
	}

	public void setComplainRaisedDate(LocalDate complainRaisedDate) {
		this.complainRaisedDate = complainRaisedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getComplainClosedDate() {
		return complainClosedDate;
	}

	public void setComplainClosedDate(LocalDate complainClosedDate) {
		this.complainClosedDate = complainClosedDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderedProd> getOrderedProd() {
		return orderedProd;
	}

	public void setOrderedProd(List<OrderedProd> orderedProd) {
		this.orderedProd = orderedProd;
	}

	public Complain() {}

	public String getComplainType() {
		return complainType;
	}

	public void setComplainType(String complainType) {
		this.complainType = complainType;
	}

	public Complain(int id, LocalDate complainRaisedDate, String description, String status,
			LocalDate complainClosedDate, String complainType, Order order, List<OrderedProd> orderedProd) {
		super();
		this.id = id;
		this.complainRaisedDate = complainRaisedDate;
		this.description = description;
		this.status = status;
		this.complainClosedDate = complainClosedDate;
		this.complainType = complainType;
		this.order = order;
		this.orderedProd = orderedProd;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Complain [id=" + id + ", complainRaisedDate=" + complainRaisedDate + ", description=" + description
				+ ", status=" + status + ", complainClosedDate=" + complainClosedDate + ", complainType=" + complainType
				+ ", order=" + order + ", orderedProd="
				+ (orderedProd != null ? orderedProd.subList(0, Math.min(orderedProd.size(), maxLen)) : null) + "]";
	}
	
	

	
	
}
