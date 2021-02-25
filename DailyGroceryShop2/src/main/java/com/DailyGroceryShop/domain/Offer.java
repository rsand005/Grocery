package com.DailyGroceryShop.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Offer implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int offerId;
	
	private String offerName;
	private String description;
	private String status;
	private int discountAmt;
	private Date validFromDate;
	private Date validToDate;
	private float minPurchase;
	private float maxPurchase;
	
	@OneToMany
	private List<Product> productList = new ArrayList<Product>();

	
	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
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

	public int getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(int discountAmt) {
		this.discountAmt = discountAmt;
	}

	public Date getValidFromDate() {
		return validFromDate;
	}

	public void setValidFromDate(Date validFromDate) {
		this.validFromDate = validFromDate;
	}

	public Date getValidToDate() {
		return validToDate;
	}

	public void setValidToDate(Date validToDate) {
		this.validToDate = validToDate;
	}

	public float getMinPurchase() {
		return minPurchase;
	}

	public void setMinPurchase(float minPurchase) {
		this.minPurchase = minPurchase;
	}

	public float getMaxPurchase() {
		return maxPurchase;
	}

	public void setMaxPurchase(float maxPurchase) {
		this.maxPurchase = maxPurchase;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", offerName=" + offerName + ", description=" + description + ", status="
				+ status + ", discountAmt=" + discountAmt + ", validFromDate=" + validFromDate + ", validToDate="
				+ validToDate + ", minPurchase=" + minPurchase + ", maxPurchase=" + maxPurchase + ", productList="
				+ productList + "]";
	}
	
	
}
