package com.DailyGroceryShop.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Address implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private int houseNo;
	private String street;
	private String city;
	private String state;
	private int zipCode;
	private String addressType;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", houseNo=" + houseNo + ", street=" + street + ", city=" + city + ", state="
				+ state + ", zipCode=" + zipCode + ", addressType=" + addressType + "]";
	}
	
	
	
}
