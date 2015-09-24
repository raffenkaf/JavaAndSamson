package com.samson.hibernate.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class HibernateOrder {
	HibernateOrder(){
	}
	
	public HibernateOrder(int id, int qty, int amount) {
		setAmount(amount);
		setOrderId(id);
		setQty(qty);		
	}
	
	@Id
	@Column(name = "order_id")
	@GeneratedValue
	private int orderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;
	
	@Column(name = "qty")
	private int qty;
	
	@Column(name = "amount")
	private int amount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="product_id"	)
	private HibernateProduct product;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="customer_id"	)
	private HibernateCustomer customer;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public HibernateProduct getProduct() {
		return product;
	}

	public void setProduct(HibernateProduct product) {
		this.product = product;
	}

	public HibernateCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(HibernateCustomer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
}
