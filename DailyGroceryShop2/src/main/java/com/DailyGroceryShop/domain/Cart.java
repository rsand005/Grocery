package com.DailyGroceryShop.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

@Entity
public class Cart implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<CartItem> listOfCartItems = new ArrayList<CartItem>();
	
	@ManyToOne
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CartItem> getListOfCartItems() {
		return listOfCartItems;
	}
	
	public void setListOfCartItems(List<CartItem> listOfCartItems) {
		this.listOfCartItems = listOfCartItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Cart [id=" + id + ", listOfCartItems="
				+ (listOfCartItems != null ? listOfCartItems.subList(0, Math.min(listOfCartItems.size(), maxLen))
						: null)
				+ ", user=" + user + "]";
	}
	
	public Cart() {}

	public Cart(int id, List<CartItem> listOfCartItems, User user) {
		super();
		this.id = id;
		this.listOfCartItems = listOfCartItems;
		this.user = user;
	}

	
	
	
	
}
