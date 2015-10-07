package com.samson.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Customer aCustomer = new Customer();
   	 
    	aCustomer.setAddress(rs.getString("address"));
    	aCustomer.setId(rs.getInt("id"));
    	aCustomer.setName(rs.getString("name"));
    	aCustomer.setPhone(rs.getString("phone"));
    	aCustomer.setRating(rs.getInt("rating"));

        return aCustomer;
	}
	
}
