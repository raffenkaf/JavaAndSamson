package com.samson.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name ="customers")
public class HibernateCustomer {
	public HibernateCustomer(){
	}
	
	public HibernateCustomer(int customerId, String name, 
			String phone, String address, float rating){
		setName(name);
		setPhone(phone);
		setAddress(address);
		setRating(rating);
		setCustomerId(customerId);
	}
	
	@Id
	@GenericGenerator(name="assigned" , strategy="assigned")
	@GeneratedValue(generator="assigned")
	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "rating")
	private float rating;
	
    @OneToOne(mappedBy = "customer",cascade=CascadeType.ALL)
	private HibernateOrder order;
    
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public HibernateOrder getOrder() {
		return order;
	}

	public void setOrder(HibernateOrder order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
