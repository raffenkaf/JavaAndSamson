package com.samson.model;

public class Customer {
	private int id = -1;
	private String name;
	private String phone;
	private String address;
	private float rating = -1;
	
	public Customer() {
	}
	
	public Customer(int outerId, String outerName, String outerPhone, String outerAddres, int outerRating){
		setId(outerId);
		setName(outerName);
		setPhone(outerPhone);
		setAddress(outerAddres);
		setRating(outerRating);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
