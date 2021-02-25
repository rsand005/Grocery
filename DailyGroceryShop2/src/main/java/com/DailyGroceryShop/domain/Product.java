package com.DailyGroceryShop.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

@Entity
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	@CsvBindByName
	private String prodName;
	
	@CsvBindByName(column = "prodDescription")
	private String prodDescription;
	
	@CsvBindByName(column ="price")
	@Min(value=(long) 1.00, message="Value should be more!")
	private float price;
	
	
	private String image;
	
	@CsvBindByName(column ="quantity")
	private int quantity;
	
	@CsvDate(value = "dd.MM.yyyy")
	@CsvBindByName(column ="dateOfExpiry")
	private Date dateOfExpiry;
	
	@CsvBindByName(column ="brand")
	private String brand; //vendor
	
	@OneToOne(cascade = CascadeType.ALL)
	private ProdInventory productInventory;
	
	@ManyToOne
	@CsvBindByName(column = "category")
	private Category category;

	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdDescription() {
		return prodDescription;
	}

	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public ProdInventory getProductInventory() {
		return productInventory;
	}

	public void setProductInventory(ProdInventory productInventory) {
		this.productInventory = productInventory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product() {}

	public Product(int productId, String prodName, String prodDescription,
			@Min(value = 1, message = "Value should be more!") float price, String image, int quantity,
			Date dateOfExpiry, String brand, ProdInventory productInventory, Category category) {
		super();
		this.productId = productId;
		this.prodName = prodName;
		this.prodDescription = prodDescription;
		this.price = price;
		this.image = image;
		this.quantity = quantity;
		this.dateOfExpiry = dateOfExpiry;
		this.brand = brand;
		this.productInventory = productInventory;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", prodName=" + prodName + ", prodDescription=" + prodDescription
				+ ", price=" + price + ", image=" + image + ", quantity=" + quantity + ", dateOfExpiry=" + dateOfExpiry
				+ ", brand=" + brand + ", productInventory=" + productInventory + ", category=" + category + "]";
	}

		
	
}
