package com.DailyGroceryShop.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Customer implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long customerId;
	
	@OneToMany
	private List<Address> addresses = new ArrayList<Address>();

	@OneToOne
	private User user;

	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", addresses=" + addresses + ", user=" + user + "]";
	}

	public Customer() {}
	
	public Customer(long customerId, List<Address> addresses, User user) {
		super();
		this.customerId = customerId;
		this.addresses = addresses;
		this.user = user;
	}
	
	
	
}
