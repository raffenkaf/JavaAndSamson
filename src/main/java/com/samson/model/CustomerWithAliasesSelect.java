package com.samson.model;

public class CustomerWithAliasesSelect extends Customer{
	private String otherCustomerName;

	public String getOtherCustomerName() {
		return otherCustomerName;
	}

	public void setOtherCustomerName(String otherCustomerName) {
		this.otherCustomerName = otherCustomerName;
	}
	 
}
