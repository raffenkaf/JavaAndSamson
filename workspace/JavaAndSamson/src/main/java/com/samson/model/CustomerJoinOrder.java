package com.samson.model;

import java.sql.Timestamp;

public class CustomerJoinOrder {
	//Customer
	private int customerId;
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	private float customerRating;
	//Order
	private int orderId;
	private Timestamp orderDate;
	private int orderProductId;
	private int orderQty;
	private int orderAmount;
	private int orderCustomerId;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public float getCustomerRating() {
		return customerRating;
	}
	public void setCustomerRating(float customerRating) {
		this.customerRating = customerRating;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public int getOrderProductId() {
		return orderProductId;
	}
	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}
	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public int getOrderCustomerId() {
		return orderCustomerId;
	}
	public void setOrderCustomerId(int orderCustomerId) {
		this.orderCustomerId = orderCustomerId;
	}
	
}
