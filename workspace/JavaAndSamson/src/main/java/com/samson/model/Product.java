package com.samson.model;

public class Product {
	private int id = -1;
	private String description;
	private String details;
	private int price = -1;
	
	public Product() {
	}
	
	public Product(int outerId, String outerDescription, 
						String outerDetails, int outerPrice) {
		setId(outerId);
		setDescription(outerDescription);
		setDetails(outerDetails);
		setPrice(outerPrice);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
