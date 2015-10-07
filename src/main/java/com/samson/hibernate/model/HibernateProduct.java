package com.samson.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class HibernateProduct {
	public HibernateProduct() {
		// TODO Auto-generated constructor stub
	}
	
	public HibernateProduct(int id, String description, String details, int price) {
		setProductId(id);
		setDescription(description);
		setDetails(details);
		setPrice(price);
	}
	
	@Id
	@Column(name = "product_id")
	@GeneratedValue
	private int productId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "price")
	private int price;
	
    @OneToOne(mappedBy = "product",cascade=CascadeType.ALL)
	private HibernateOrder order;
    
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public HibernateOrder getOrder() {
		return order;
	}

	public void setOrder(HibernateOrder order) {
		this.order = order;
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
