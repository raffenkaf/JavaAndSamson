package com.samson.model;

import java.sql.Timestamp;

public class Order {
	private int id = -1;
	private Timestamp date;
	private int productId = -1;
	private int qty = -1;
	private int amount = -1;
	private int customerId = -1;
	
	public Order() {
	}
	
	public Order(int outerId, Timestamp outerDate, int outerProductId, 
					int outerQty, int outerAmount, int outerCustomerId){
		id = outerId;
		date = outerDate;
		productId = outerProductId;
		qty = outerQty;
		amount = outerAmount;
		customerId = outerCustomerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
